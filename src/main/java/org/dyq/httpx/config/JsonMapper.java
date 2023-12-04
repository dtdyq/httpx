package org.dyq.httpx.config;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

public interface JsonMapper {
    byte[] marshal(Object val) throws Exception;

    void marshal(Object val, OutputStream os) throws Exception;

    <T> T unmarshal(byte[] data, Type type) throws Exception;

    <T> T unmarshal(InputStream is, Type type) throws Exception;
}
