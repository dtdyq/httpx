package org.dyq.httpx.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.Buffer;

@Data
@Accessors(fluent = true)
public class Config {
    private int port = 8080;
    private int backlog = 1024;
    private JsonMapper jsonMapper = new JacksonMapper();

    private BufferConfig buffer = BufferConfig.curr();


    private static final Config inst = new Config();

    private Config() {

    }

    public static Config curr() {
        return inst;
    }
}
