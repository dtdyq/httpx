package org.dyq.httpx.core;

import lombok.extern.slf4j.Slf4j;
import org.dyq.httpx.config.Config;
import org.dyq.httpx.core.stream.PeakInputStream;
import org.dyq.httpx.resp.Response;
import org.dyq.httpx.resp.impl.Except;
import org.dyq.httpx.resp.impl.Status;
import org.dyq.httpx.route.RouterManager;
import org.dyq.httpx.util.CommonUtil;
import org.dyq.httpx.util.HeaderNames;
import org.dyq.httpx.util.RespCacheUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Session implements Runnable {
    private final Config config;
    private String uuid;
    private final Socket tcpSocket;
    private PeakInputStream pis;
    private OutputStream bos;
    private Request request;
    public static final byte[] CRLF = new byte[]{'\r', '\n'};
    public static final byte[] COLON = new byte[]{':'};

    public Session(Socket client, Config config) {
        this.uuid = CommonUtil.uuid();
        this.tcpSocket = client;
        this.config = config;
    }

    @Override
    public void run() {
        try {
            try {
                tcpSocket.setSoTimeout(5 * 1000); // todo options
                this.pis = PeakInputStream.of(tcpSocket.getInputStream());
                this.bos = tcpSocket.getOutputStream();
                request = RequestParser.parseToHeader(this, this.pis);
                checkHeaderAndProc();
            } catch (Throwable e) {
                if (e instanceof ErrRetBytesException ee) {
                    bos.write(ee.data);
                }
                // 这个阶段出现错误，就直接关闭这个session吧 ,头都没读完
                log.info("header parse and proc error.", e);
                tcpSocket.close();
                return;
            }
            handleIt();

            tcpSocket.close();
        } catch (Throwable e) {
            log.error("session exception ", e);
            try {
                tcpSocket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void handleIt() {
        Context c = new Context(this);
        List<Handler> h = RouterManager.inst().handlerFor(this);
        c.reInstallHandlers(h);
        try (Response writable = c.next()) {
            Objects.requireNonNullElseGet(writable, () -> Status.of(RespStatus.NOT_FOUND)).write(c);
        } catch (Throwable e) {
            except = e;
            Map<Class<? extends Throwable>, List<Handler>> routeMap = RouterManager.inst().exceptionHandlers();
            List<Handler> handlers = new ArrayList<>();
            for (Map.Entry<Class<? extends Throwable>, List<Handler>> entry : routeMap.entrySet()) {
                Class<? extends Throwable> cc = entry.getKey();
                if (cc.isInstance(e)) {
                    handlers.addAll(entry.getValue());
                }
            }
            if (handlers.isEmpty()) {
                try (Response r = Except.svrError(e)) {
                    r.write(c);
                } catch (Throwable exc) {
                    throw new RuntimeException(exc);
                }
            } else {
                c.reInstallHandlers(handlers);
                try (Response w = c.next()) {
                    Objects.requireNonNullElseGet(w, () -> Status.of(RespStatus.NOT_FOUND)).write(c);
                } catch (Throwable ex) {
                    except = ex;
                    try (Response r = Except.svrError(e)) {
                        r.write(c);
                    } catch (Throwable exc) {
                        throw new RuntimeException(exc);
                    }
                }
            }
        }
    }

    private void checkHeaderAndProc() throws Throwable {
        var headers = request.rawHeader();
        long contentLen = 0L;
        String headerValue = null;
        List<String> teValueList = headers.get(HeaderNames.TRANSFER_ENCODING);
        if (teValueList != null && !teValueList.isEmpty()) {
            headerValue = teValueList.get(0);
        }
        if (headerValue != null) {
            if (headerValue.equalsIgnoreCase("chunked") && teValueList.size() == 1) {
                contentLen = -1L;
            } else {
                if (request.isProtoV10()) {
                    throw new ErrRetBytesException(RespCacheUtil.RSP_UN_SUPPORT_HEADER_ERROR_V0);
                } else {
                    throw new ErrRetBytesException(RespCacheUtil.RSP_UN_SUPPORT_HEADER_ERROR_V1);
                }
            }
        } else {
            List<String> headerList = headers.get(HeaderNames.CONTENT_LENGTH);
            if (headerList != null && !headerList.isEmpty()) {
                headerValue = headerList.get(0);
                if (headerValue != null) {
                    try {
                        contentLen = Long.parseLong(headerValue);
                    } catch (NumberFormatException e2) {
                        if (request.isProtoV10()) {
                            throw new ErrRetBytesException(RespCacheUtil.RSP_UN_SUPPORT_HEADER_ERROR_V0);
                        } else {
                            throw new ErrRetBytesException(RespCacheUtil.RSP_UN_SUPPORT_HEADER_ERROR_V1);
                        }
                    }
                    if (contentLen < 0) {
                        if (request.isProtoV10()) {
                            throw new ErrRetBytesException(RespCacheUtil.RSP_CONTENT_LENGTH_ERROR_V0);
                        } else {
                            throw new ErrRetBytesException(RespCacheUtil.RSP_CONTENT_LENGTH_ERROR_V1);
                        }
                    }
                }
            }
        }
        request.size(contentLen);
    }

    public Request request() {
        return request;
    }

    public OutputStream os() {
        return bos;
    }

    public PeakInputStream rawIs() {
        return pis;
    }

    private Throwable except;

    public Throwable exception() {
        return except;
    }

    public String uuid() {
        return uuid;
    }

    public static class ErrRetBytesException extends Exception {
        private final byte[] data;

        public ErrRetBytesException(byte[] data) {
            this.data = data;
        }
    }
}
