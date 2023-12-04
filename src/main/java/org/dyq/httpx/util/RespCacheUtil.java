package org.dyq.httpx.util;

import org.dyq.httpx.core.RespStatus;

import java.nio.charset.StandardCharsets;

public class RespCacheUtil {
    public static final byte[] RSP_REQ_LINE_ERROR_METHOD = """
            HTTP/1.1 %d
            Content-Length: 18
            Content-Type: text/plain

            bad request method""".formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RSP_REQ_LINE_ERROR_URI = """
            HTTP/1.1 %d
            Content-Length: 15
            Content-Type: text/plain

            bad request URI""".formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RSP_REQ_LINE_ERROR_PROTOCOL_UN_SUPPORT = """
            HTTP/1.1 %d
            Content-Length: 30
            Content-Type: text/plain

            bad request version un support""".formatted(RespStatus.HTTP_VERSION_NOT_SUPPORTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RSP_UN_SUPPORT_HEADER_ERROR_V1 = """
            HTTP/1.1 %d
            Content-Length: 18
            Content-Type: text/plain

            bad request header""".formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RSP_UN_SUPPORT_HEADER_ERROR_V0 = """
            HTTP/1.0 %d
            Content-Length: 18
            Content-Type: text/plain

            bad request header""".formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RSP_CONTENT_LENGTH_ERROR_V1 = """
            HTTP/1.1 %d
            Content-Length: 22
            Content-Type: text/plain

            invalid content-length""".formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RSP_CONTENT_LENGTH_ERROR_V0 = """
            HTTP/1.0 %d
            Content-Length: 22
            Content-Type: text/plain

            invalid content-length""".formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
}
