package org.dyq.httpx.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.dyq.httpx.config.Config;
import org.dyq.httpx.core.Context;
import org.dyq.httpx.core.RespStatus;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.dyq.httpx.core.Session.COLON;
import static org.dyq.httpx.core.Session.CRLF;

public abstract class NoBodyResponse implements Response {
    @Setter
    @Getter
    @Accessors(fluent = true)
    protected int status = RespStatus.OK;
    protected Map<String, Object> headers = new HashMap<>();

    @Override
    public void writeCode(Context ctx) throws Throwable {
        if (ctx.protoV11()) {
            ctx.rawOs().write(RespStatus.v1RspBytes(status));
        } else {
            ctx.rawOs().write(RespStatus.v0RspBytes(status));
        }
    }

    @Override
    public void writeHeader(Context ctx) throws Throwable {
        List<byte[]> chunk = new ArrayList<>();
        int total = 0;
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            String os = entry.getValue().toString();
            byte[] tmp = HeaderNames.bytesOf(entry.getKey());
            total += tmp.length;
            total += 1;
            chunk.add(tmp);
            chunk.add(COLON);
            tmp = HeaderValues.bytesOf(os, os.getBytes(StandardCharsets.ISO_8859_1));
            total += tmp.length;
            chunk.add(tmp);
            total += 2;
            chunk.add(CRLF);
        }
        chunk.add(CRLF);
        total += 2;
        byte[] tt = new byte[total];
        final int[] from = {0};
        chunk.forEach(new Consumer<byte[]>() {
            @Override
            public void accept(byte[] bytes) {
                int f = from[0];
                System.arraycopy(bytes, 0, tt, f, bytes.length);
                from[0] += bytes.length;
            }
        });
        ctx.rawOs().write(tt);
    }

    @Override
    public void writeBody(Context ctx) throws Throwable {
        // do nothing
    }

    @Override
    public void close() throws Exception {
        // do no
    }


    private static final byte[] CHUNKED_END_BYTES = (Integer.toHexString(0) + "\r\n\r\n").getBytes(StandardCharsets.UTF_8);

    protected void writeChunked(Context ctx, InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] tmp = new byte[Config.curr().buffer().getRespSize()];
        int read;
        while ((read = bis.read(tmp)) != -1) {
            ctx.rawOs().write((Integer.toHexString(read) + "\r\n").getBytes(StandardCharsets.UTF_8));
            ctx.rawOs().write(tmp, 0, read);
            ctx.rawOs().write(new byte[]{'\r', '\n'});
        }
        ctx.rawOs().write(CHUNKED_END_BYTES);
    }

    protected void writeBytes(Context ctx, byte[] data) throws IOException {
        writeBytes(ctx, data, 0, data.length);
    }

    protected void writeBytes(Context ctx, byte[] data, int offset, int len) throws IOException {
        ctx.rawOs().write(data, offset, len);
    }

}
