package org.dyq.httpx.core.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.charset.Charset;

@Data
@Accessors(fluent = true)
public class FormData {
    private String name;
    private String filename;
    private Charset charset;
    private byte[] bytes;

    private boolean byteF = true;
    private String data;

    public String str() {
        if (!byteF && data == null) {
            if (bytes == null) {
                return null;
            }
            return new String(bytes, charset);
        } else {
            return data;
        }
    }

    public static FormData strForm(String k, String v) {
        return new FormData().name(k).byteF(false).data(v);
    }
}
