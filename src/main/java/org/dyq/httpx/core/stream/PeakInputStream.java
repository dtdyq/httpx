package org.dyq.httpx.core.stream;

import org.dyq.httpx.core.ByteSlice;
import org.dyq.httpx.exception.SocketReadException;
import org.dyq.httpx.util.Config;

import java.io.IOException;
import java.io.InputStream;

/**
 * 所有返回byteSlice的函数，都是直接返回内部缓冲区的映射,
 * 需要保证：
 * 下次读取的时候，已经处理完了所有上次读取的数据，否则会被内部修改
 * 不要修改byteSlice内部的任何数据，是个只读的结构
 */
public class PeakInputStream {
    private ByteSlice src;
    private InputStream is;
    private final ByteSlice bs = new ByteSlice().data(new byte[Config.RECEIVE_BUFFER_SIZE.getInt()]);

    PeakInputStream(InputStream is) {
        this.is = is;
    }

    public PeakInputStream(ByteSlice src) {
        this.src = src;
        bs.merge(src);
    }

    public static PeakInputStream of(InputStream is) {
        return new PeakInputStream(is);
    }

    protected int cacheSize() {
        return bs.data().length;
    }

    /**
     * 读完的话，抛异常
     *
     * @return
     */
    public byte read() {
        ensureAvailable();
        return bs.data()[bs.getAndIncrPos()];
    }

    public byte view() {
        ensureAvailable();
        return bs.data()[bs.pos()];
    }

    /**
     * 读取当前所有的有效数据,不是把所有的都读完了(未必)
     *
     * @return bs
     */
    public ByteSlice readMany() {
        ensureAvailable();
        return bs;
    }

    public ByteSlice readN(int n) {
        if (bs.available() >= n) {
            ByteSlice cp = new ByteSlice().data(bs.data()).pos(bs.pos()).end(bs.pos() + n);
            bs.pos(bs.pos() + n);
            return cp;
        }
        bs.expend(n);
        while (bs.available() < n) {
            doRead();
        }
        ByteSlice cp = new ByteSlice().data(bs.data()).pos(bs.pos()).end(bs.pos() + n);
        bs.pos(bs.pos() + n);
        return cp;
    }

    private void ensureAvailable() {
        if (bs.available() <= 0) {
            doRead();
        }
    }


    private void doRead() {
        if (src != null) {
            throw new SocketReadException("socket end");
        }
        try {
            bs.pos(0);
            bs.end(is.read(bs.data(), bs.pos(), bs.data().length));
            if (bs.end() == -1) {
                throw new SocketReadException("socket closed");
            }
        } catch (IOException e) {
            throw new SocketReadException(e);
        }
    }
}
