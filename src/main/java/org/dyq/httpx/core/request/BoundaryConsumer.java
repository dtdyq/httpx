package org.dyq.httpx.core.request;

import org.dyq.httpx.core.ByteSlice;

import java.util.List;
import java.util.Map;

public interface BoundaryConsumer {
    void start(Map<String, List<String>> headers) throws Throwable;

    void consume(ByteSlice b) throws Throwable;

    void finish() throws Throwable;

}
