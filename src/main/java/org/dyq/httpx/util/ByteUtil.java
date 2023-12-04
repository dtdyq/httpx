package org.dyq.httpx.util;

import org.dyq.httpx.core.ByteSlice;

import java.nio.charset.StandardCharsets;

public class ByteUtil {

    private static final int len = 2049;
    public static final ByteSlice[] CHUNKED_LEN_CACHE_MAP = new ByteSlice[len];
    public static final char CR = 13;
    public static final char LF = 10;

    static {
        for (int i = 0; i < len; i++) {
            CHUNKED_LEN_CACHE_MAP[i] = ByteSlice.wrap((Integer.toHexString(i) + "\r\n").getBytes(StandardCharsets.UTF_8));
        }
    }

    public static ByteSlice chunkLenBytesOf(int i) {
        if (i >= len) {
            return ByteSlice.wrap((Integer.toHexString(i) + "\r\n").getBytes(StandardCharsets.UTF_8));
        }
        return CHUNKED_LEN_CACHE_MAP[i];
    }
}
