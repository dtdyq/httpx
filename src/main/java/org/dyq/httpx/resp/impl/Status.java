package org.dyq.httpx.resp.impl;

import org.dyq.httpx.resp.DefaultResponse;

public class Status extends DefaultResponse {
    public static Status of(int code) {
        Status status = new Status();
        status.status = code;
        return status;
    }
}
