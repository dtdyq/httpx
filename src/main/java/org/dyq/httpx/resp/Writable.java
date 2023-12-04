package org.dyq.httpx.resp;

import org.dyq.httpx.core.Context;

public interface Writable {
    void write(Context ctx) throws Throwable;
}
