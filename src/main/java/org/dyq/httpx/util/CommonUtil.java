package org.dyq.httpx.util;

import java.util.UUID;

public class CommonUtil {
    public static String uuid() {
        String guid = UUID.randomUUID().toString();
        return guid.substring(0, 8) + guid.substring(9, 13)
                + guid.substring(14, 18) + guid.substring(19, 23)
                + guid.substring(24);
    }
}
