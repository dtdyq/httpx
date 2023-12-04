package org.dyq.httpx.core;

import lombok.extern.slf4j.Slf4j;
import org.dyq.httpx.core.stream.PeakInputStream;
import org.dyq.httpx.exception.ReqParseException;
import org.dyq.httpx.exception.SocketReadException;
import org.dyq.httpx.util.ByteUtil;
import org.dyq.httpx.util.RespCacheUtil;

import java.net.URI;

import static org.dyq.httpx.util.HttpMethod.*;

@Slf4j
public class RequestParser {

    private static void parseHeaders(PeakInputStream pis, Request request) {
        StringBuilder tmp = new StringBuilder(24);
        boolean lfPre = false;
        boolean cr = false;
        boolean lineFirstColon = false;
        String currKey = null;
        while (true) {
            byte b = pis.read();
            if (b == -1) {
                throw new SocketReadException("socket closed");
            }
            char c = (char) b;
            if (c == ByteUtil.LF) {
                if (lfPre) {// 如果lfPre是true，一定是header结束了
                    break;
                } else {//否则一定是下一个header
                    if (currKey == null) {
                        throw new ReqParseException("invalid header format");
                    }
                    lineFirstColon = false;
                    String tmpVal = tmp.toString();
                    log.debug("read new header value:{}", tmpVal);
                    if (tmpVal.isEmpty()) {
                        request.addHeader(currKey, tmpVal);
                    } else {
                        request.addHeader(currKey, tmpVal.substring(tmpVal.charAt(0) == ' ' ? 1 : 0, cr ? tmpVal.length() - 1 : tmpVal.length()));
                    }
                    tmp.setLength(0);
                    currKey = null;
                }
                lfPre = true;
            } else {
                if (c == ':') {
                    if (!lineFirstColon) {
                        lineFirstColon = true;
                        currKey = tmp.toString();
                        tmp.setLength(0);
                        log.debug("read new key:{}", currKey);
                    }
                } else {
                    cr = c == ByteUtil.CR;
                    if (c >= 'A' && c <= 'Z') { // 判断是否为大写
                        c = (char) (c + (char) 32); // 转换为小写
                    }
                    tmp.append(c);
                    lfPre = lfPre && cr;
                }
            }
        }
        log.info("headers read end:{}", request);
    }

    private static void parseReqLine(PeakInputStream pis, Request request) throws Throwable {
        StringBuilder line = new StringBuilder();
        boolean cr = false;
        boolean firstBlackIdx = false;
        boolean secondBlackIdx = false;
        while (true) {
            byte b = pis.read();
            if (b == -1) {
                throw new SocketReadException("socket closed");
            }
            char c = (char) b;
            if (c == ByteUtil.LF) {
                break;
            } else {
                if (c == ' ') {
                    if (!firstBlackIdx) {
                        String method = line.toString();
                        checkValidMethod(method);
                        request.method(method);
                        line.setLength(0);
                        firstBlackIdx = true;
                    } else if (!secondBlackIdx) {
                        String uri = line.toString();
                        try {
                            request.uri(URI.create(uri));
                        } catch (Throwable e) {
                            throw new Session.ErrRetBytesException(RespCacheUtil.RSP_REQ_LINE_ERROR_URI);
                        }
                        line.setLength(0);
                        secondBlackIdx = true;
                    } else {
                        throw new ReqParseException("req line invalid");
                    }
                } else {
                    line.append(c);
                    cr = c == ByteUtil.CR;
                }
            }
        }
        char l = cr ? line.charAt(line.length() - 2) : line.charAt(line.length()-1);
        if (l != '0' && l != '1') {
            throw new Session.ErrRetBytesException(RespCacheUtil.RSP_REQ_LINE_ERROR_PROTOCOL_UN_SUPPORT);
        }
        request.proto(line.substring(0, cr ? line.length() - 1 : line.length()).trim());
        log.info("read req line:{}", request);
    }

    private static final String[] VALID_METHODS = new String[]{GET, PUT, POST, OPTIONS, HEAD, DELETE, TRACE, CONNECT};

    private static void checkValidMethod(String method) throws Session.ErrRetBytesException {
        for (String s : VALID_METHODS) {
            if (s.equals(method)) {
                return;
            }
        }
        throw new Session.ErrRetBytesException(RespCacheUtil.RSP_REQ_LINE_ERROR_METHOD);
    }

    public static boolean validHeaderC(char c) {
        return c <= 255 && TCHAR[c];
    }

    private static final boolean[] TCHAR = new boolean[256];

    static {
        char[] allowedTokenChars =
                ("!#$%&'*+-.^_`|~0123456789" +
                        "abcdefghijklmnopqrstuvwxyz" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        for (char c : allowedTokenChars) {
            TCHAR[c] = true;
        }
    }

    /**
     * 解析到只剩下body或者没有body
     *
     * @param session
     * @param pis     p
     * @return
     */
    public static Request parseToHeader(Session session, PeakInputStream pis) throws Throwable {
        Request request = new Request(session);
        parseReqLine(pis, request);
        parseHeaders(pis, request);
        return request;
    }
}
