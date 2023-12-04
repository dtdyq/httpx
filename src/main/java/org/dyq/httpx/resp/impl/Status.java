package org.dyq.httpx.resp.impl;

import org.dyq.httpx.resp.NoBodyResponse;

public class Status extends NoBodyResponse {
    public static Status of(int code) {
        Status status = new Status();
        status.status = code;
        return status;
    }
}
