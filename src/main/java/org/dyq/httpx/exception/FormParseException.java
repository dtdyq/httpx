package org.dyq.httpx.exception;

public class FormParseException extends RuntimeException {
    public FormParseException(String msg) {
        super(msg, null);
    }

    public FormParseException(Throwable e) {
        super(null, e);
    }

    public FormParseException(String msg, Throwable e) {
        super(msg, e);
    }
}
