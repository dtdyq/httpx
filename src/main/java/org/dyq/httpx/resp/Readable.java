package org.dyq.httpx.resp;

import org.dyq.httpx.core.ByteSlice;

@FunctionalInterface
public interface Readable {
    /**
     * 必须返回一个byteSlice
     * 用于系统写入数据，read返回的start==end的时候，就不会再调用了
     *
     * @return bs
     */
    ByteSlice read() throws Throwable;

    default void close() {

    }
}
