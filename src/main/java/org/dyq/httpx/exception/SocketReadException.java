package org.dyq.httpx.exception;

public class SocketReadException extends RuntimeException {
    public SocketReadException(String msg) {
        super(msg, null);
    }

    public SocketReadException(Throwable e) {
        super(null, e);
    }

    public SocketReadException(String msg, Throwable e) {
        super(msg, e);
    }
}
