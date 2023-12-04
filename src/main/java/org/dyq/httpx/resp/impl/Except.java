package org.dyq.httpx.resp.impl;

import org.dyq.httpx.core.RespStatus;
import org.dyq.httpx.resp.DefaultResponse;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;
import org.dyq.httpx.xh.Context;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Except extends DefaultResponse {
    private Throwable raw;
    private byte[] rawBytes;

    public static Except svrError(Throwable e) {
        Except except = new Except();
        except.status = RespStatus.INTERNAL_SERVER_ERROR;
        except.headers.put(HeaderNames.CONTENT_TYPE, HeaderValues.TEXT_PLAIN);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(bos));
        except.raw = e;
        except.rawBytes = bos.toByteArray();
        except.headers.put(HeaderNames.CONTENT_LENGTH, except.rawBytes.length);
        return except;
    }

    @Override
    public void writeBody(Context ctx) throws Throwable {
        ctx.rawOs().write(rawBytes);
    }
}
