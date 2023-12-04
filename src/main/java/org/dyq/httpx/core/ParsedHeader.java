package org.dyq.httpx.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 彻底解析过得header
 */
@Data
@Accessors(fluent = true)
public class ParsedHeader {
    private String key;
    private List<String> value = new ArrayList<>();
    private Map<String, String> param = new HashMap<>();

    public String value(int i) {
        if (value.size() > i) {
            return value.get(i);
        }
        return null;
    }
}
