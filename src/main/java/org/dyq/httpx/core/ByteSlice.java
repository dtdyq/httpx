package org.dyq.httpx.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.charset.Charset;

@Data
@Accessors(fluent = true)
public class ByteSlice {
    public static final ByteSlice EMPTY = new ByteSlice();

    private byte[] data;

    private int pos;

    private int end;

    public int getAndIncrPos() {
        pos++;
        return pos - 1;
    }

    /**
     * 申请一个byteSlice
     *
     * @param size
     * @return
     */
    public static ByteSlice of(int size) {
        return new ByteSlice().data(new byte[size]);
    }

    public static ByteSlice wrap(byte[] raw) {
        return wrap(raw, 0, raw.length);
    }

    public static ByteSlice wrap(byte[] raw, int pos, int end) {
        return new ByteSlice().data(raw).pos(pos).end(end);
    }

    public int available() {
        return end - pos;
    }

    public void expend(int n) {
        if (n <= data.length) {
            return;
        }
        byte[] tmp = data;
        int available = available();
        data = new byte[n];
        System.arraycopy(tmp, pos, data, 0, available);
        end = available;
        pos = 0;
    }

    private void compact() {
        if (pos == 0 || available() <= 0 || data == null) {
            return;
        }
        int available = available();
        System.arraycopy(data, pos, data, 0, available);
        pos = 0;
        end = available;
    }

    public void merge(ByteSlice bs) {
        if (data.length - end < bs.available()) {
            if (data.length - available() >= bs.available()) {
                compact();
            } else {
                expend(data.length + bs.available());
            }
        }
        System.arraycopy(bs.data, bs.pos, data, end, bs.available());
        end += bs.available();
    }

    public byte[] copyToArray() {
        byte[] result = new byte[available()];
        System.arraycopy(data, pos, result, 0, available());
        return result;
    }

    public void clear() {
        pos = 0;
        end = 0;
    }

    public ByteSlice duplicate() {
        ByteSlice tmp = new ByteSlice();
        byte[] b = new byte[available()];
        System.arraycopy(data, pos, b, 0, b.length);
        tmp.data(b);
        tmp.end(b.length);
        return tmp;
    }

    public String toStr(Charset charset) {
        return new String(data, pos, pos + available(), charset);
    }
}
