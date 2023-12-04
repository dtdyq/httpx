package org.dyq.httpx.resp.impl;

import org.dyq.httpx.core.MimeMapping;
import org.dyq.httpx.core.RespStatus;
import org.dyq.httpx.resp.IsCloserResponse;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;

import java.io.InputStream;

public class Stream extends IsCloserResponse {
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
}
