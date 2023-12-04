package org.dyq.httpx.core.header;

import org.dyq.httpx.core.ParsedHeader;

import java.util.List;

@FunctionalInterface
public interface HeaderParser {
    ParsedHeader parse(String key, List<String> vls);
}
