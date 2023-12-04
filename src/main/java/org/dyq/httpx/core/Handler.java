package org.dyq.httpx.core;

import org.dyq.httpx.resp.Response;

public interface Handler {
    Response handle(Context ctx) throws Throwable;
}
