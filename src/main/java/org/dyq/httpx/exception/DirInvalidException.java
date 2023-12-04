package org.dyq.httpx.exception;

public class DirInvalidException extends RuntimeException {
    public DirInvalidException(String msg) {
        super(msg, null);
    }

    public DirInvalidException(Throwable e) {
        super(null, e);
    }

    public DirInvalidException(String msg, Throwable e) {
        super(msg, e);
    }
}
