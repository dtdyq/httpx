package org.dyq.httpx.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.dyq.httpx.core.Context;
import org.dyq.httpx.core.RespStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BytesResponse extends NoBodyResponse {
    @Setter
    @Getter
    @Accessors(fluent = true)
    protected int status = RespStatus.OK;
    protected Map<String, Object> headers = new HashMap<>();

    protected byte[] data;

    @Override
    public void writeBody(Context ctx) throws Throwable {
        writeBytes(ctx, data);
    }

}
