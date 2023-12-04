package org.dyq.httpx.xh;

import org.dyq.httpx.resp.Response;

public interface Handler {
    Response handle(Context ctx) throws Throwable;
}
