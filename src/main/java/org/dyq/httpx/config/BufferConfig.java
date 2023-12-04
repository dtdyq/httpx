package org.dyq.httpx.config;

import lombok.Data;

@Data
public class BufferConfig {
    private int receive = 2048;
    private int respSize = 2048;
    private int respChunkSize = 1024 * 1024;
    private int multipartMemoryMax = 1024 * 1024 * 4;

    private static final BufferConfig inst = new BufferConfig();

    private BufferConfig() {
    }

    public static BufferConfig curr() {
        return inst;
    }
}
