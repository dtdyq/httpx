package org.dyq.httpx.core.stream;

import org.dyq.httpx.core.ByteSlice;
import org.dyq.httpx.util.ByteUtil;

import java.io.InputStream;

public class ChunkedInputStream {
    private final PeakInputStream pis;
    private boolean first = true;
    private int remainCurChunk = 0;

    public ChunkedInputStream(PeakInputStream is) {
        this.pis = is;
    }

    /**
     * 读取数据，忽略chunked编码数据
     *
     * @return
     */
    public ByteSlice readSkipChunk() {
        if (!first && remainCurChunk == 0) {
            pis.read();
            pis.read();//CRLF
        }
        first = false;
        if (remainCurChunk == 0) {
            remainCurChunk = readLenField();
            if (remainCurChunk == 0) {
                pis.read();
                pis.read();
                return ByteSlice.EMPTY;
            }
        }
        if (remainCurChunk <= pis.cacheSize()) {
            ByteSlice tmp = pis.readN(remainCurChunk);
            remainCurChunk = 0;
            return tmp;
        } else {
            remainCurChunk -= pis.cacheSize();
            return pis.readN(pis.cacheSize());
        }
    }

    private int readLenField() {
        StringBuilder len = new StringBuilder();
        while (true) {
            byte c = pis.read();
            char cc = (char) c;
            if (cc == ByteUtil.LF) {
                break;
            }
            if (cc != ByteUtil.CR) {
                len.append(cc);
            }
        }
        return Integer.parseInt(len.toString(), 16);
    }
}
