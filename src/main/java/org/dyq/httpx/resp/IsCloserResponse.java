package org.dyq.httpx.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.dyq.httpx.config.Config;
import org.dyq.httpx.core.Context;
import org.dyq.httpx.core.RespStatus;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class IsCloserResponse extends NoBodyResponse {
    @Setter
    @Getter
    @Accessors(fluent = true)
    protected int status = RespStatus.OK;
    protected Map<String, Object> headers = new HashMap<>();

    protected InputStream is;

    @Override
    public void writeBody(Context ctx) throws Throwable {
        writeChunked(ctx, is);
    }


    @Override
    public void close() throws Exception {
        is.close();
    }
}
