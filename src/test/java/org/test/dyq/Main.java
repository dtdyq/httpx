package org.test.dyq;

import org.dyq.httpx.core.Context;
import org.dyq.httpx.core.CustomException;
import org.dyq.httpx.core.Handler;
import org.dyq.httpx.core.HttpX;
import org.dyq.httpx.core.request.MultipartFileResolver;
import org.dyq.httpx.resp.Response;
import org.dyq.httpx.resp.impl.Except;
import org.dyq.httpx.resp.impl.Json;
import org.junit.Test;

public class Main {
    public static record Data(int v, String d) {
    }

    public static void main(String[] a) throws Throwable {
        HttpX.create()
                .post("/up", new Handler() {
                    @Override
                    public Response handle(Context ctx) throws Throwable {
                        ctx.resolveFiles(MultipartFileResolver.toDir("./upload"));
                        System.out.println(ctx.forms());
                        return Json.ok(new Data(12, "aaa"));
                    }
                }).start()
                .dir("/", "./")
                .except(CustomException.class, new Handler() {
                    @Override
                    public Response handle(Context ctx) throws Throwable {
                        return Except.svrError(ctx.exception());
                    }
                }).start();
    }

    @Test
    public void testStatic() throws Throwable {
        HttpX.create()
                .dir("/", "./").start();
    }
}