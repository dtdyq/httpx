package org.dyq.httpx.resp.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.dyq.httpx.resp.DefaultResponse;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.HeaderValues;
import org.dyq.httpx.xh.Context;

@Data
@Accessors(fluent = true)
public class Json extends DefaultResponse {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectWriter writer = mapper.writer();
    private byte[] data;

    public static Json ok(Object val) {
        return ofStatus(val, 200);
    }

    public static Json badRequest(Object val) {
        return ofStatus(val, 400);
    }

    public static Json svrError(Object val) {
        return ofStatus(val, 500);
    }

    private static Json ofStatus(Object val, int status) {
        try {
            byte[] b = mapper.writeValueAsBytes(val);
            Json json = new Json();
            json.status(status);
            json.headers.put(HeaderNames.CONTENT_TYPE, "%s;%s=UTF-8".formatted(HeaderValues.APPLICATION_JSON, HeaderValues.CHARSET));
            json.headers.put(HeaderNames.CONTENT_LENGTH, b.length);
            json.data(b);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeBody(Context ctx) throws Throwable {
        ctx.rawOs().write(data);
    }
}
