package org.dyq.httpx.resp.impl;

import org.dyq.httpx.core.RespStatus;
import org.dyq.httpx.resp.BytesResponse;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Except extends BytesResponse {
    private Throwable raw;

    public static Except svrError(Throwable e) {
        Except except = new Except();
        except.status = RespStatus.INTERNAL_SERVER_ERROR;
        except.headers.put(HeaderNames.CONTENT_TYPE, HeaderValues.TEXT_PLAIN);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(bos));
        except.raw = e;
        except.data = bos.toByteArray();
        except.headers.put(HeaderNames.CONTENT_LENGTH, except.data.length);
        return except;
    }
}
