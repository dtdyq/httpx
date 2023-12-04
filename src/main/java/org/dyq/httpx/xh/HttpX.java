package org.dyq.httpx.xh;

import org.dyq.httpx.core.HttpSvr;
import org.dyq.httpx.route.RouterManager;
import org.dyq.httpx.util.HttpMethod;

public class HttpX {
    private final HttpSvr svr = new HttpSvr();

    private HttpX() {
    }

    /**
     * effective for all routes
     *
     * @param handler h
     * @return slf
     */
    public HttpX use(Handler handler) {
        RouterManager.inst().global(handler);
        return this;
    }

    /**
     * server a static dir
     * relative path or jar resource with a abs path
     *
     * @param prefix uri prefix
     * @param path   local path
     * @return slf
     */
    public HttpX dir(String prefix, String path) {
        RouterManager.inst().dir(prefix, path);
        return this;
    }

    /**
     * git handler
     *
     * @param uri     uri
     * @param handler handler
     * @return slf
     */
    public HttpX get(String uri, Handler handler) {
        RouterManager.inst().exact(HttpMethod.GET, uri, handler);
        return this;
    }

    public HttpX get(Handler handler) {
        RouterManager.inst().method(HttpMethod.GET, handler);
        return this;
    }

    public HttpX post(String uri, Handler handler) {
        RouterManager.inst().exact(HttpMethod.POST, uri, handler);
        return this;
    }

    public HttpX post(Handler handler) {
        RouterManager.inst().method(HttpMethod.POST, handler);
        return this;
    }

    public HttpX regex(String regex, Handler handler) {
        RouterManager.inst().regex(regex, handler);
        return this;
    }

    public HttpX except(Class<? extends Exception> clz, Handler handler) {
        RouterManager.inst().except(clz, handler);
        return this;
    }

    public static HttpX create() {
        return new HttpX();
    }

    public HttpX server(int addr) throws Throwable {
        RouterManager.inst().complete();
        svr.start();
        return this;
    }

    public void stop() {
        svr.stop();
    }
}
