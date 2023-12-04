package org.dyq.httpx.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

public class JacksonMapper implements JsonMapper {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] marshal(Object val) throws JsonProcessingException {
        return mapper.writeValueAsBytes(val);
    }

    @Override
    public void marshal(Object val, OutputStream os) throws IOException {
        mapper.writeValue(os, val);
    }

    @Override
    public <T> T unmarshal(byte[] data, Type type) throws IOException {
        return mapper.readValue(data, mapper.getTypeFactory().constructType(type));
    }

    @Override
    public <T> T unmarshal(InputStream is, Type type) throws IOException {
        return mapper.readValue(is, mapper.getTypeFactory().constructType(type));
    }
}
