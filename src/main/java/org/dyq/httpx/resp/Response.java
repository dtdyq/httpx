package org.dyq.httpx.resp;

import org.dyq.httpx.xh.Context;

/**
 * 一个response表示
 */
public interface Response extends Writable, AutoCloseable {
    default void write(Context ctx) throws Throwable {
        writeCode(ctx);
        writeHeader(ctx);
        writeBody(ctx);
    }

    void writeCode(Context ctx) throws Throwable;

    void writeHeader(Context ctx) throws Throwable;

    void writeBody(Context ctx) throws Throwable;
}
