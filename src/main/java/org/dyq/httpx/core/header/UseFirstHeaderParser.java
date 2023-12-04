package org.dyq.httpx.core.header;

import org.dyq.httpx.core.ParsedHeader;

import java.util.List;

public class UseFirstHeaderParser implements HeaderParser {
    @Override
    public ParsedHeader parse(String key, List<String> vls) {
        if (vls == null || vls.isEmpty()) {
            return null;
        }
        String v = vls.get(0);
        if (v == null || v.isBlank()) {
            return null;
        }
        ParsedHeader ph = new ParsedHeader();
        ph.key(key);
        for (String s : v.split(",")) {
            if (s == null || s.isBlank()) {
                continue;
            }
            if (s.contains(";")) {
                String[] vp = s.split(";");
                if (vp[0].isBlank()) {
                    continue;
                }
                ph.value().add(vp[0].strip());
                for (int i = 1; i < vp.length; i++) {
                    String pp = vp[i];
                    if (pp.isBlank()) {
                        continue;
                    }
                    if (pp.contains("=")) {
                        String[] ppv = pp.split("=");
                        if (ppv.length != 2) {
                            continue;
                        }
                        ph.param().put(ppv[0].strip(), ppv[1].strip());
                    } else {
                        ph.param().put(pp.strip(), null);
                    }
                }
            } else {
                ph.value().add(s.strip());
            }
        }
        return ph;
    }
}
