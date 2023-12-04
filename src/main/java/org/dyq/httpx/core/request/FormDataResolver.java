package org.dyq.httpx.core.request;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dyq.httpx.core.ByteSlice;
import org.dyq.httpx.core.ParsedHeader;
import org.dyq.httpx.core.Session;
import org.dyq.httpx.core.header.HeaderUtil;
import org.dyq.httpx.util.Config;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo Content-Transfer-Encoding 缺一个这个头部待支持
 * <p>
 * 默认的multipart/form-data解析器，会将所有key - value解析成map
 * 所有file 解析成map:String->byte[]
 * 如果某个file的数据超过Config.MULTIPART_FILE_MEMORY_MAX_SIZE 则写入临时文件
 */
@Slf4j
public class FormDataResolver implements BoundaryConsumer {
    protected static final String CHARSET_NAME = "_charset_";
    protected final Map<String, List<String>> rawHeader = new HashMap<>();
    protected final Session session;
    protected boolean invalid = false;
    protected String name;
    protected String filename;
    protected ByteArrayOutputStream tmp = new ByteArrayOutputStream(512);
    protected boolean isFile = false;
    protected Charset currentCharset = StandardCharsets.UTF_8;
    @Setter
    protected int fileMemoryMaxSize = Config.MULTIPART_FILE_MEMORY_MAX_SIZE.getInt();
    @Getter
    protected final Map<String, String> formMap = new HashMap<>();
    @Getter
    protected final Map<String, MultipartFile> fileMap = new HashMap<>();
    protected Path tmpFile;
    protected BufferedOutputStream tmpOs;

    public FormDataResolver(Session session) {
        this.session = session;
    }

    @Override
    public void start(Map<String, List<String>> headers) throws Throwable {
        reset();
        rawHeader.putAll(headers);
        if (!rawHeader.containsKey(HeaderNames.CONTENT_DISPOSITION)) {
            invalid = true;
            log.warn("invalid form no content disposition:{}", rawHeader);
            return;
        }
        ParsedHeader ps = HeaderUtil.USE_FIRST.parse(HeaderNames.CONTENT_DISPOSITION, rawHeader.get(HeaderNames.CONTENT_DISPOSITION));
        name = ps.param().get(HeaderValues.NAME);
        filename = ps.param().get(HeaderValues.FILENAME);
        if (name == null && filename == null) {
            invalid = true;
            log.warn("invalid form no content disposition name param:{}", rawHeader);
            return;
        }
        isFile = filename != null;
        if (name != null && name.startsWith("\"") && name.endsWith("\"")) {
            name = name.substring(1, name.length() - 1);
        }
        if (filename != null && filename.startsWith("\"") && filename.endsWith("\"")) {
            filename = filename.substring(1, filename.length() - 1);
        }
        if (rawHeader.containsKey(HeaderNames.CONTENT_TYPE)) {
            ps = HeaderUtil.USE_FIRST.parse(HeaderNames.CONTENT_DISPOSITION, rawHeader.get(HeaderNames.CONTENT_DISPOSITION));
            if (ps != null) {
                currentCharset = Charset.forName(ps.param().getOrDefault(HeaderValues.CHARSET, "utf-8"));
            }
        }
    }

    protected void reset() throws IOException {
        isFile = false;
        invalid = false;
        name = null;
        filename = null;
        tmpFile = null;
        if (tmpOs != null) {
            tmpOs.close();
        }
        tmpOs = null;
        rawHeader.clear();
        tmp = new ByteArrayOutputStream();
    }

    @Override
    public void consume(ByteSlice b) throws Throwable {
        if (!invalid) {
            if (isFile && tmp.size() >= fileMemoryMaxSize) {
                if (tmpFile == null) {
                    tmpFile = Files.createTempFile(Paths.get(System.getenv("user.dir")), session.uuid(), filename);
                    tmpOs = new BufferedOutputStream(Files.newOutputStream(tmpFile, StandardOpenOption.WRITE));
                    tmp.writeTo(tmpOs);
                }
                tmpOs.write(b.data(),b.pos(),b.available());
            } else {
                tmp.write(b.data(),b.pos(),b.available());
            }
        }
    }

    @Override
    public void finish() throws Throwable {
        if (!invalid) {
            if (CHARSET_NAME.equals(name)) {
                currentCharset = Charset.forName(tmp.toString(currentCharset));
            } else {
                if (isFile) {
                    MultipartFile multipartFile = new MultipartFile().filename(filename);
                    multipartFile.charset(currentCharset);
                    if (tmpFile != null) {
                        multipartFile.memory(false);
                        multipartFile.tempFile(tmpFile);
                    } else {
                        multipartFile.memory(true);
                        multipartFile.data(tmp.toByteArray());
                    }
                } else {
                    formMap.put(name, tmp.toString(currentCharset));
                }
            }
        }
    }

}
