package org.dyq.httpx.core.request;

import org.dyq.httpx.core.ByteSlice;
import org.dyq.httpx.core.ParsedHeader;
import org.dyq.httpx.core.Session;
import org.dyq.httpx.core.header.HeaderUtil;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * multipart form 转 向用户提供的FIle Resolver适配器,用来处理form-data的文件操作
 * 和父类不同的是，它处理文件使用用户定制的MpFileResolver，而不是父类内置的resolver实现
 */
public class BoundaryXFileResolveAdapter extends FormDataResolver {
    private final MultipartFileResolver resolver;

    public BoundaryXFileResolveAdapter(Session session, MultipartFileResolver resolver) {
        super(session);
        this.resolver = resolver;
    }

    @Override
    public void start(Map<String, List<String>> rawHeader) throws Throwable {
        super.start(rawHeader);
        if (invalid) {
            return;
        }
        if (isFile) {
            String ctt = HeaderValues.APPLICATION_OCTET_STREAM;
            if (rawHeader.containsKey(HeaderNames.CONTENT_TYPE)) {
                ParsedHeader ct = HeaderUtil.USE_FIRST.parse(HeaderNames.CONTENT_TYPE, rawHeader.get(HeaderNames.CONTENT_TYPE));
                ctt = ct.value(0);
            }
            MultipartFileResolver.FileInfo fileInfo = new MultipartFileResolver.FileInfo(filename, ctt, currentCharset);
            resolver.start(fileInfo);
        }
    }

    @Override
    public void consume(ByteSlice b) throws Throwable {
        if (!invalid) {
            if (isFile) {
                resolver.incoming(b);
            } else {
                tmp.write(b.data(), b.pos(), b.available());
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
                    resolver.finish();
                } else {
                    formMap.put(name, tmp.toString(currentCharset));
                }
            }
        }
    }
}
