package org.dyq.httpx.resp;

import org.dyq.httpx.xh.Context;

import java.io.OutputStream;

public interface Writable {
    void write(Context ctx) throws Throwable;
}
