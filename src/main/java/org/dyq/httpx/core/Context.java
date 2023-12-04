package org.dyq.httpx.core;

import org.dyq.httpx.core.request.MultipartFileResolver;
import org.dyq.httpx.resp.Response;
import org.dyq.httpx.route.util.CharArray;

import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Context {
    private final List<Handler> handlers = new ArrayList<>();
    private int index = 0;
    private final Session session;

    Context(Session session) {
        this.session = session;
    }

    void reInstallHandlers(List<Handler> handlers) {
        this.handlers.clear();
        this.handlers.addAll(handlers);
        index = 0;
    }

    public Response next() throws Throwable {
        if (index < handlers.size()) {
            Handler handler = handlers.get(index++);
            return handler.handle(this);
        }
        return null;
    }

    public String uri() {
        return session.request().uri().getPath();
    }

    public String path(String name) {
        return path(name, null);
    }

    public String path(String name, String def) {
        CharArray c = session.request().pathVariables().get(name);
        if (c == null) {
            return def;
        }
        return c.toString();
    }

    public String query(String key) {
        return query(key, null);
    }

    public String query(String key, String def) {
        Map<String, String> qm = session.request().queryMap();
        var v = qm.get(key);
        if (v == null) {
            return def;
        }
        return v;
    }

    public boolean protoV11() {
        return session.request().isProtoV11();
    }

    public OutputStream rawOs() {
        return session.os();
    }

    public String form(String name) throws Throwable {
        return form(name, null);
    }

    public String form(String name, String def) throws Throwable {
        return session.request().formData(name, def);
    }

    public Map<String, String> forms() throws Throwable {
        return session.request().formStrMap();
    }

    public byte[] body() {
        return session.request().body();
    }
    public String asStr() {
        return session.request().asStr();
    }

    public <T> T json(Type type) throws Exception {
        return session.request().asJson(type);
    }

    public Throwable exception() {
        return session.exception();
    }

    public void resolveFiles(MultipartFileResolver resolver) throws Throwable {
        session.request().fileResolve(resolver);
    }
}
