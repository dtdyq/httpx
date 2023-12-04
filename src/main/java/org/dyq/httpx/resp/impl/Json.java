package org.dyq.httpx.resp.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.dyq.httpx.config.Config;
import org.dyq.httpx.core.Context;
import org.dyq.httpx.resp.NoBodyResponse;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(fluent = true)
public class Json extends NoBodyResponse {
    private byte[] data;

    public static Json ok(Object val) throws Exception {
        return ofStatus(val, 200);
    }

    public static Json badRequest(Object val) throws Exception {
        return ofStatus(val, 400);
    }

    public static Json svrError(Object val) throws Exception {
        return ofStatus(val, 500);
    }

    private static Json ofStatus(Object val, int status) throws Exception {
        byte[] b = Config.curr().jsonMapper().marshal(val);
        Json json = new Json();
        json.status(status);
        json.headers.put(HeaderNames.CONTENT_TYPE, "%s;%s=UTF-8".formatted(HeaderValues.APPLICATION_JSON, HeaderValues.CHARSET));
        json.headers.put(HeaderNames.CONTENT_LENGTH, b.length);
        json.data(b);
        return json;
    }

    @Override
    public void writeBody(Context ctx) throws Throwable {
        ctx.rawOs().write(data);
    }
}
