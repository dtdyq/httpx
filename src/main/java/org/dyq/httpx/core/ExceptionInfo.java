package org.dyq.httpx.core;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ExceptionInfo {
    private Throwable e;
}
