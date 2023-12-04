package org.dyq.httpx.exception;

public class SocketSetupException extends RuntimeException {
    public SocketSetupException(String msg) {
        super(msg, null);
    }

    public SocketSetupException(Throwable e) {
        super(null, e);
    }

    public SocketSetupException(String msg, Throwable e) {
        super(msg, e);
    }
}
