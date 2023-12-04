package org.dyq.httpx.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.dyq.httpx.core.header.HeaderParser;
import org.dyq.httpx.core.header.HeaderUtil;
import org.dyq.httpx.core.request.BoundaryXFileResolveAdapter;
import org.dyq.httpx.core.request.FormDataResolver;
import org.dyq.httpx.core.request.MultipartFileResolver;
import org.dyq.httpx.core.stream.ChunkedInputStream;
import org.dyq.httpx.core.stream.PeakInputStream;
import org.dyq.httpx.exception.FormParseException;
import org.dyq.httpx.route.util.CharArray;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;

@EqualsAndHashCode
@ToString
@Slf4j
public class Request {
    private final Session session;
    @Getter
    @Setter
    @Accessors(fluent = true)
    private String method;
    @Getter
    @Setter
    @Accessors(fluent = true)
    private URI uri;
    @Getter
    @Accessors(fluent = true)
    private String proto;
    @Setter
    @Getter
    @Accessors(fluent = true)
    private long size = 0L;

    private Map<String, String> qm = null;

    @Getter
    @Accessors(fluent = true)
    private final Map<String, CharArray> pathVariables = new HashMap<>();

    @Getter
    @Accessors(fluent = true)
    private final Map<String, List<String>> rawHeader = new HashMap<>();

    private final boolean[] httpProto = new boolean[3];

    private boolean bodyReadFinish = false;


    // 默认的form解析器，如果用户提供新的，则替换当前的
    @Setter
    private FormDataResolver formResolver;

    public Request(Session session) {
        this.session = session;
        this.formResolver = new FormDataResolver(session);
    }

    public Request addHeader(String key, String val) {
        rawHeader.compute(key, (k, strings) -> {
            if (strings == null) {
                strings = new ArrayList<>();
            }
            strings.add(val);
            return strings;
        });
        return this;
    }

    public Request addPathVars(Map<String, CharArray> variables) {
        pathVariables.putAll(variables);
        return this;
    }

    public Map<String, String> queryMap() {
        if (qm == null) {
            qm = new HashMap<>();
            String raw = uri.getRawQuery();
            for (String kv : raw.split("&")) {
                String[] kvs = kv.split("=");
                if (kvs.length != 2) {
                    log.error("invalid query param:{}", kv);
                    continue;
                }
                qm.put(URLDecoder.decode(kvs[0], StandardCharsets.UTF_8), URLDecoder.decode(kvs[1], StandardCharsets.UTF_8));
            }
        }
        return qm;
    }

    public boolean isProtoV10() {
        return httpProto[0];
    }

    public boolean isProtoV11() {
        return httpProto[1];
    }

    public boolean isProtoV2() {
        return httpProto[2];
    }

    public Request proto(String proto) {
        httpProto[0] = proto.charAt(proto.length() - 1) == '0';
        httpProto[1] = proto.charAt(proto.length() - 1) == '1';
        httpProto[2] = proto.charAt(proto.length() - 1) == '2';
        this.proto = proto;
        return this;
    }

    private ByteSlice raw = ByteSlice.EMPTY;

    public byte[] body() {
        if (!bodyReadFinish) {
            raw = switch ((int) size) {
                case 0 -> ByteSlice.EMPTY;
                case -1 -> {
                    var cis = new ChunkedInputStream(session.rawIs());
                    ByteSlice bs;
                    ByteSlice total = ByteSlice.of(1024);
                    while ((bs = cis.readSkipChunk()) != ByteSlice.EMPTY) {
                        total.merge(bs);
                    }
                    yield total;
                }
                default -> session.rawIs().readN((int) size);
            };
            bodyReadFinish = true;
        }
        return raw.copyToArray();
    }

    private boolean formParsed = false;

    private final Map<String, String> generalFormDataMap = new HashMap<>();


    public Map<String, String> formStrMap() throws Throwable {
        ensureFormAndParsed();
        return generalFormDataMap;
    }

    public String formData(String name, String def) throws Throwable {
        ensureFormAndParsed();
        return generalFormDataMap.getOrDefault(name, def);
    }

    private void ensureFormAndParsed() throws Throwable {
        if (formParsed) {
            return;
        }
        ParsedHeader ps = parseHeader(HeaderNames.CONTENT_TYPE, HeaderUtil.USE_FIRST);
        if (ps == null || (!Objects.equals(ps.value(0), HeaderValues.MULTIPART_FORM_DATA)
                && !Objects.equals(ps.value(0), HeaderValues.APPLICATION_X_WWW_FORM_URLENCODED))) {
            return;
        }
        Charset charset = Charset.forName(ps.param().getOrDefault(HeaderValues.CHARSET, "utf-8"));
        formParsed = true;
        if (size != 0) {
            if (size == -1) { // chunked
                if (Objects.equals(ps.value(0), HeaderValues.APPLICATION_X_WWW_FORM_URLENCODED)) {
                    if (bodyReadFinish) {
                        parseUrlEncodedFormedData(ps, charset, raw);
                    } else {
                        var cis = new ChunkedInputStream(session.rawIs());
                        ByteSlice bs;
                        ByteSlice total = ByteSlice.of(1024);
                        while ((bs = cis.readSkipChunk()) != ByteSlice.EMPTY) {
                            total.merge(bs);
                        }
                        parseUrlEncodedFormedData(ps, charset, total);
                    }
                } else {
                    ChunkedInputStream cis;
                    if (bodyReadFinish) {
                        cis = new ChunkedInputStream(new PeakInputStream(raw));
                    } else {
                        cis = new ChunkedInputStream(session.rawIs());
                    }
                    parseMultipartFormData(charset, cis, null);
                    generalFormDataMap.putAll(formResolver.getFormMap());
                }
            } else { //fixLength
                if (Objects.equals(ps.value(0), HeaderValues.APPLICATION_X_WWW_FORM_URLENCODED)) {
                    if (size > Integer.MAX_VALUE) {
                        throw new FormParseException("content too large to bind");
                    }
                    ByteSlice bs = bodyReadFinish ? raw : session.rawIs().readN((int) size);
                    parseUrlEncodedFormedData(ps, charset, bs);
                } else {
                    parseMultipartFormData(charset, null, bodyReadFinish ? new PeakInputStream(raw) : session.rawIs());
                    generalFormDataMap.putAll(formResolver.getFormMap());
                }
            }
        }  // 没有body体

    }

    private void parseMultipartFormData(Charset charset, ChunkedInputStream cis, PeakInputStream pis) throws Throwable {
        ParsedHeader header = parseHeader(HeaderNames.CONTENT_TYPE, HeaderUtil.USE_FIRST);
        assert header != null;
        String boundary = header.param().get(HeaderValues.BOUNDARY);
        byte[] boundaryBytes = ("--" + boundary + "\r\n").getBytes();
        byte[] noFirstBoundaryBytes = ("\r\n--" + boundary).getBytes();
        int[] boundaryTable = computePrefixFunction(noFirstBoundaryBytes);
        int firstBoundaryLen = boundaryBytes.length;
        ByteSlice tmp;
        boolean headerField = false;
        boolean bodyField = false;
        boolean occurBoundary = false;
        ByteSlice buffer = ByteSlice.of(2048);
        ByteArrayOutputStream headerBuilder = new ByteArrayOutputStream();
        byte[] HEADER_SEPARATOR = {0x0D, 0x0A, 0x0D, 0x0A};
        int headerSeparator = 0;

        readMore(cis, pis, buffer);
        while (buffer.available() > 0) {
            while (firstBoundaryLen > 0) {
                if (buffer.available() > 0) {
                    buffer.getAndIncrPos();
                    firstBoundaryLen--;
                    headerField = firstBoundaryLen == 0;
                } else {
                    readMore(cis, pis, buffer);
                }
            }
            while (buffer.available() < 2) {
                readMore(cis, pis, buffer);
            }
            if (bodyField && occurBoundary) {
                // 可能是body，也可能是最后的--或者CRLF
                if (buffer.data()[buffer.pos()] == 0x0D && buffer.data()[buffer.pos() + 1] == 0x0A) {
                    buffer.pos(buffer.pos() + 2);
                    bodyField = false;
                    headerField = true;
                    occurBoundary = false;
                    formResolver.finish();
                } else if (buffer.data()[buffer.pos()] == 0x2D && buffer.data()[buffer.pos() + 1] == 0x2D) {
                    // all done
                    formResolver.finish();
                    if (buffer.end() > buffer.pos() + 2) {
                        buffer.pos(buffer.pos() + 2);
                        System.out.println("::" + Arrays.toString(buffer.copyToArray()));
                    }
                    return;
                }
            }

            if (headerField) {
                while (buffer.available() > 0 && headerSeparator < HEADER_SEPARATOR.length) {
                    byte b = buffer.data()[buffer.getAndIncrPos()];
                    if (b == HEADER_SEPARATOR[headerSeparator]) {
                        headerSeparator++;
                    } else {
                        headerSeparator = 0;
                    }
                    headerBuilder.write(b);
                }
                if (headerSeparator == HEADER_SEPARATOR.length) {
                    headerField = false;
                    headerSeparator = 0;
                    bodyField = true;
                    List<String> hl = Arrays.stream(headerBuilder.toString(charset).strip().split("\r\n")).toList();
                    System.out.println(hl);
                    formResolver.start(parseHeaderLines(hl));
                    headerBuilder.reset();
                }
            } else if (bodyField) {
                while (buffer.available() < noFirstBoundaryBytes.length) {
                    readMore(cis, pis, buffer);
                }
                int pos = kmpMatcher(buffer, noFirstBoundaryBytes, boundaryTable);
                int tmpEnd = buffer.end();
                if (pos == -1) {
                    buffer.end(buffer.end() - (noFirstBoundaryBytes.length - 1));

                    formResolver.consume(buffer);
                    buffer.pos(buffer.end());
                    buffer.end(tmpEnd);
                    readMore(cis, pis, buffer);
                } else {
                    buffer.end(pos);
                    formResolver.consume(buffer);
                    buffer.pos(pos + noFirstBoundaryBytes.length);
                    buffer.end(tmpEnd);
                    occurBoundary = true;
                }
            }
        }
    }

    private int[] computePrefixFunction(byte[] pattern) {
        int[] prefix = new int[pattern.length];
        int k = 0;
        for (int q = 1; q < pattern.length; q++) {
            while (k > 0 && pattern[k] != pattern[q]) {
                k = prefix[k - 1];
            }
            if (pattern[k] == pattern[q]) {
                k++;
            }
            prefix[q] = k;
        }
        return prefix;
    }

    public int kmpMatcher(ByteSlice text, byte[] pattern, int[] prefix) {
        int m = pattern.length;
        int q = 0;
        for (int i = text.pos(); i < text.end(); i++) {
            while (q > 0 && pattern[q] != text.data()[i]) {
                q = prefix[q - 1];
            }
            if (pattern[q] == text.data()[i]) {
                q++;
            }
            if (q == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    private Map<String, List<String>> parseHeaderLines(List<String> headers) {
        Map<String, List<String>> rawHeader = new HashMap<>();
        for (String header : headers) {
            int idx = header.indexOf(":");
            if (idx == -1) {
                continue;
            }
            String key = header.substring(0, idx).trim().toLowerCase();
            String value = header.substring(idx + 1).toLowerCase();
            List<String> vals = rawHeader.getOrDefault(key, new ArrayList<>());
            vals.add(value);
            rawHeader.put(key, vals);
        }
        return rawHeader;
    }

    private boolean isMatchFullBoundary(byte[] buffer, byte[] boundary, int writeIdx) {
        for (byte b : boundary) {
            if (buffer[(writeIdx++) % boundary.length] != b) {
                return false;
            }
        }
        return true;
    }

    private void readMore(ChunkedInputStream cis, PeakInputStream pis, ByteSlice buffer) {
        ByteSlice tmp = cis != null ? cis.readSkipChunk() : pis.readMany();
        buffer.merge(tmp);
        tmp.clear();
    }

    private void parseUrlEncodedFormedData(ParsedHeader ps, Charset charset, ByteSlice bs) {
        parseUrlEncoded(bs, charset).forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String k, String v) {
                generalFormDataMap.put(k, v);
            }
        });
    }

    private final Map<String, ParsedHeader> parsedHeaderMap = new HashMap<>();

    private ParsedHeader parseHeader(String key, HeaderParser parser) {
        if (parsedHeaderMap.containsKey(key)) {
            return parsedHeaderMap.get(key);
        }
        if (!rawHeader.containsKey(key) || rawHeader.get(key).isEmpty()) {
            return null;
        }
        ParsedHeader p = parser.parse(key, rawHeader.get(key));
        if (p == null) {
            return null;
        }
        parsedHeaderMap.put(key, p);
        return p;
    }

    Map<String, String> parseUrlEncoded(ByteSlice bytes, Charset charset) {
        Map<String, String> map = new HashMap<>();
        String data = new String(bytes.data(), bytes.pos(), bytes.available(), charset);
        String[] pairs = data.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx == -1) {
                log.warn("invalid xxx url encoded format:{} skip!", pair);
                continue;
            }
            String key = URLDecoder.decode(pair.substring(0, idx), charset);
            String value = URLDecoder.decode(pair.substring(idx + 1), charset);
            map.put(key, value);
        }

        return map;
    }

    /**
     * 处理文件解析，这里需要先判断form 解析过没有，如果解析过，就需要使用form解析的结果，否则开始解析，并同时解析form
     *
     * @param resolver r
     * @throws Throwable t
     */
    public void fileResolve(MultipartFileResolver resolver) throws Throwable {
        ParsedHeader ps = parseHeader(HeaderNames.CONTENT_TYPE, HeaderUtil.USE_FIRST);
        if (ps == null || !Objects.equals(ps.value(0), HeaderValues.MULTIPART_FORM_DATA)) {
            log.warn("try file resolve mismatch content type:{}", ps == null ? "null" : ps.value(0));
            return;
        }
        Charset charset = Charset.forName(ps.param().getOrDefault(HeaderValues.CHARSET, "utf-8"));
        formParsed = true;
        if (size != 0) {
            // 这个会把文件写入resolver，普通form放到formMap
            formResolver = new BoundaryXFileResolveAdapter(session, resolver);
            if (size == -1) { // chunked
                ChunkedInputStream cis;
                if (bodyReadFinish) {
                    cis = new ChunkedInputStream(new PeakInputStream(raw));
                } else {
                    cis = new ChunkedInputStream(session.rawIs());
                }
                parseMultipartFormData(charset, cis, null);
                generalFormDataMap.putAll(formResolver.getFormMap());
            } else { //fixLength
                parseMultipartFormData(charset, null, bodyReadFinish ? new PeakInputStream(raw) : session.rawIs());
                generalFormDataMap.putAll(formResolver.getFormMap());
            }
        }
    }
}
