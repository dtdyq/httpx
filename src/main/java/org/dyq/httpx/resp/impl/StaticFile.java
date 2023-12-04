package org.dyq.httpx.resp.impl;

import org.dyq.httpx.core.MimeMapping;
import org.dyq.httpx.core.RespStatus;
import org.dyq.httpx.resp.DefaultResponse;
import org.dyq.httpx.util.Config;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;
import org.dyq.httpx.xh.Context;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StaticFile extends DefaultResponse {

    //todo 这里应该可以设置
    private InputStream is;
    private byte[] bytes;


    public static StaticFile ok(Path path) {
        try {
            StaticFile f = new StaticFile();
            if (!Files.exists(path)) {
                f.status = RespStatus.NOT_FOUND;
            } else {
                f.status = RespStatus.OK;
                var mmt = MimeMapping.getMimeTypeForFilename(path.getFileName().toString(), HeaderValues.APPLICATION_OCTET_STREAM);
                f.headers.put(HeaderNames.CONTENT_TYPE, mmt);
                long size = Files.size(path);
                if (size > Config.RESP_CHUNKED_MIN_SIZE.getLong()) {
                    f.is = Files.newInputStream(path, StandardOpenOption.READ);
                    f.headers.put(HeaderNames.TRANSFER_ENCODING, HeaderValues.CHUNKED);
                } else {
                    f.bytes = Files.readAllBytes(path);
                    f.headers.put(HeaderNames.CONTENT_LENGTH, f.bytes.length);
                }
            }
            return f;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeBody(Context ctx) throws Throwable {
        if (bytes != null) {
            ctx.rawOs().write(bytes);
        } else {
            writeChunked(is, ctx.rawOs());
        }
    }

    @Override
    public void close() throws Exception {
        if (is != null) {
            is.close();
        }
    }
}
