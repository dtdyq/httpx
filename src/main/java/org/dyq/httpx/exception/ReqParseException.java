package org.dyq.httpx.exception;

public class ReqParseException extends RuntimeException {
    public ReqParseException(String msg) {
        super(msg, null);
    }

    public ReqParseException(Throwable e) {
        super(null, e);
    }

    public ReqParseException(String msg, Throwable e) {
        super(msg, e);
    }
}
