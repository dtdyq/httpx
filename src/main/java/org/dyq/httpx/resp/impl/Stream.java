package org.dyq.httpx.resp.impl;

import org.dyq.httpx.core.MimeMapping;
import org.dyq.httpx.core.RespStatus;
import org.dyq.httpx.resp.DefaultResponse;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;
import org.dyq.httpx.xh.Context;

import java.io.InputStream;

public class Stream extends DefaultResponse {

    private InputStream is;

    public static Stream ok(InputStream is, String filename) {
        Stream stream = new Stream();
        if (is == null) {
            stream.status = RespStatus.NOT_FOUND;
        } else {
            var mmt = MimeMapping.getMimeTypeForFilename(filename, HeaderValues.APPLICATION_OCTET_STREAM);
            stream.status = RespStatus.OK;
            stream.headers.put(HeaderNames.CONTENT_TYPE, mmt);
            stream.headers.put(HeaderNames.TRANSFER_ENCODING, HeaderValues.CHUNKED);
            stream.is = is;
        }
        return stream;
    }

    public static Stream ok(InputStream is) {
        return ok(is, null);
    }

    @Override
    public void writeBody(Context ctx) throws Throwable {
        writeChunked(is, ctx.rawOs());
    }

    @Override
    public void close() throws Exception {
        if (is != null) {
            is.close();
        }
    }
}
