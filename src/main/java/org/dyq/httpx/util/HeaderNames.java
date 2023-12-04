package org.dyq.httpx.util;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HeaderNames {
    public static final String ACCEPT = "accept";

    public static final String ACCEPT_CHARSET = "accept-charset";

    public static final String ACCEPT_ENCODING = "accept-encoding";

    public static final String ACCEPT_LANGUAGE = "accept-language";

    public static final String ACCEPT_RANGES = "accept-ranges";

    public static final String ACCEPT_PATCH = "accept-patch";

    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "access-control-allow-credentials";

    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "access-control-allow-headers";

    public static final String ACCESS_CONTROL_ALLOW_METHODS = "access-control-allow-methods";

    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "access-control-allow-origin";

    public static final String ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK = "access-control-allow-private-network";

    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "access-control-expose-headers";

    public static final String ACCESS_CONTROL_MAX_AGE = "access-control-max-age";

    public static final String ACCESS_CONTROL_REQUEST_HEADERS = "access-control-request-headers";

    public static final String ACCESS_CONTROL_REQUEST_METHOD = "access-control-request-method";

    public static final String ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK = "access-control-request-private-network";

    public static final String AGE = "age";

    public static final String ALLOW = "allow";

    public static final String AUTHORIZATION = "authorization";

    public static final String CACHE_CONTROL = "cache-control";

    public static final String CONNECTION = "connection";

    public static final String CONTENT_BASE = "content-base";

    public static final String CONTENT_ENCODING = "content-encoding";

    public static final String CONTENT_LANGUAGE = "content-language";

    public static final String CONTENT_LENGTH = "content-length";

    public static final String CONTENT_LOCATION = "content-location";

    public static final String CONTENT_TRANSFER_ENCODING = "content-transfer-encoding";

    public static final String CONTENT_DISPOSITION = "content-disposition";

    public static final String CONTENT_MD5 = "content-md5";

    public static final String CONTENT_RANGE = "content-range";

    public static final String CONTENT_SECURITY_POLICY = "content-security-policy";

    public static final String CONTENT_TYPE = "content-type";

    public static final String COOKIE = "cookie";

    public static final String DATE = "date";

    public static final String DNT = "dnt";

    public static final String ETAG = "etag";

    public static final String EXPECT = "expect";

    public static final String EXPIRES = "expires";

    public static final String FROM = "from";

    public static final String HOST = "host";

    public static final String IF_MATCH = "if-match";

    public static final String IF_MODIFIED_SINCE = "if-modified-since";

    public static final String IF_NONE_MATCH = "if-none-match";

    public static final String IF_RANGE = "if-range";

    public static final String IF_UNMODIFIED_SINCE = "if-unmodified-since";

    public static final String KEEP_ALIVE = "keep-alive";

    public static final String LAST_MODIFIED = "last-modified";

    public static final String LOCATION = "location";

    public static final String MAX_FORWARDS = "max-forwards";

    public static final String ORIGIN = "origin";

    public static final String PRAGMA = "pragma";

    public static final String PROXY_AUTHENTICATE = "proxy-authenticate";

    public static final String PROXY_AUTHORIZATION = "proxy-authorization";

    public static final String PROXY_CONNECTION = "proxy-connection";

    public static final String RANGE = "range";

    public static final String REFERER = "referer";

    public static final String RETRY_AFTER = "retry-after";

    public static final String SEC_WEBSOCKET_KEY1 = "sec-websocket-key1";

    public static final String SEC_WEBSOCKET_KEY2 = "sec-websocket-key2";

    public static final String SEC_WEBSOCKET_LOCATION = "sec-websocket-location";

    public static final String SEC_WEBSOCKET_ORIGIN = "sec-websocket-origin";

    public static final String SEC_WEBSOCKET_PROTOCOL = "sec-websocket-protocol";

    public static final String SEC_WEBSOCKET_VERSION = "sec-websocket-version";

    public static final String SEC_WEBSOCKET_KEY = "sec-websocket-key";

    public static final String SEC_WEBSOCKET_ACCEPT = "sec-websocket-accept";

    public static final String SEC_WEBSOCKET_EXTENSIONS = "sec-websocket-extensions";

    public static final String SERVER = "server";

    public static final String SET_COOKIE = "set-cookie";

    public static final String SET_COOKIE2 = "set-cookie2";

    public static final String TE = "te";

    public static final String TRAILER = "trailer";

    public static final String TRANSFER_ENCODING = "transfer-encoding";

    public static final String UPGRADE = "upgrade";

    public static final String UPGRADE_INSECURE_REQUESTS = "upgrade-insecure-requests";

    public static final String USER_AGENT = "user-agent";

    public static final String VARY = "vary";

    public static final String VIA = "via";

    public static final String WARNING = "warning";

    public static final String WEBSOCKET_LOCATION = "websocket-location";

    public static final String WEBSOCKET_ORIGIN = "websocket-origin";

    public static final String WEBSOCKET_PROTOCOL = "websocket-protocol";

    public static final String WWW_AUTHENTICATE = "www-authenticate";

    public static final String X_FRAME_OPTIONS = "x-frame-options";

    public static final String X_REQUESTED_WITH = "x-requested-with";

    public static final String ALT_SVC = "alt-svc";

    public static final String NONE = "NONE";

    public static final byte[] ACCEPT_BYTES = ACCEPT.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCEPT_CHARSET_BYTES = ACCEPT_CHARSET.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCEPT_ENCODING_BYTES = ACCEPT_ENCODING.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCEPT_LANGUAGE_BYTES = ACCEPT_LANGUAGE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCEPT_RANGES_BYTES = ACCEPT_RANGES.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCEPT_PATCH_BYTES = ACCEPT_PATCH.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_ALLOW_CREDENTIALS_BYTES = ACCESS_CONTROL_ALLOW_CREDENTIALS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_ALLOW_HEADERS_BYTES = ACCESS_CONTROL_ALLOW_HEADERS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_ALLOW_METHODS_BYTES = ACCESS_CONTROL_ALLOW_METHODS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_ALLOW_ORIGIN_BYTES = ACCESS_CONTROL_ALLOW_ORIGIN.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK_BYTES = ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_EXPOSE_HEADERS_BYTES = ACCESS_CONTROL_EXPOSE_HEADERS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_MAX_AGE_BYTES = ACCESS_CONTROL_MAX_AGE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_REQUEST_HEADERS_BYTES = ACCESS_CONTROL_REQUEST_HEADERS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_REQUEST_METHOD_BYTES = ACCESS_CONTROL_REQUEST_METHOD.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK_BYTES = ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] AGE_BYTES = AGE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ALLOW_BYTES = ALLOW.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] AUTHORIZATION_BYTES = AUTHORIZATION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CACHE_CONTROL_BYTES = CACHE_CONTROL.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONNECTION_BYTES = CONNECTION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_BASE_BYTES = CONTENT_BASE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_ENCODING_BYTES = CONTENT_ENCODING.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_LANGUAGE_BYTES = CONTENT_LANGUAGE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_LENGTH_BYTES = CONTENT_LENGTH.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_LOCATION_BYTES = CONTENT_LOCATION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_TRANSFER_ENCODING_BYTES = CONTENT_TRANSFER_ENCODING.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_DISPOSITION_BYTES = CONTENT_DISPOSITION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_MD5_BYTES = CONTENT_MD5.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_RANGE_BYTES = CONTENT_RANGE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_SECURITY_POLICY_BYTES = CONTENT_SECURITY_POLICY.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] CONTENT_TYPE_BYTES = CONTENT_TYPE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] COOKIE_BYTES = COOKIE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] DATE_BYTES = DATE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] DNT_BYTES = DNT.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ETAG_BYTES = ETAG.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] EXPECT_BYTES = EXPECT.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] EXPIRES_BYTES = EXPIRES.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] FROM_BYTES = FROM.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] HOST_BYTES = HOST.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] IF_MATCH_BYTES = IF_MATCH.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] IF_MODIFIED_SINCE_BYTES = IF_MODIFIED_SINCE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] IF_NONE_MATCH_BYTES = IF_NONE_MATCH.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] IF_RANGE_BYTES = IF_RANGE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] IF_UNMODIFIED_SINCE_BYTES = IF_UNMODIFIED_SINCE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] KEEP_ALIVE_BYTES = KEEP_ALIVE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] LAST_MODIFIED_BYTES = LAST_MODIFIED.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] LOCATION_BYTES = LOCATION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] MAX_FORWARDS_BYTES = MAX_FORWARDS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ORIGIN_BYTES = ORIGIN.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] PRAGMA_BYTES = PRAGMA.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] PROXY_AUTHENTICATE_BYTES = PROXY_AUTHENTICATE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] PROXY_AUTHORIZATION_BYTES = PROXY_AUTHORIZATION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] PROXY_CONNECTION_BYTES = PROXY_CONNECTION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] RANGE_BYTES = RANGE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] REFERER_BYTES = REFERER.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] RETRY_AFTER_BYTES = RETRY_AFTER.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_KEY1_BYTES = SEC_WEBSOCKET_KEY1.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_KEY2_BYTES = SEC_WEBSOCKET_KEY2.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_LOCATION_BYTES = SEC_WEBSOCKET_LOCATION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_ORIGIN_BYTES = SEC_WEBSOCKET_ORIGIN.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_PROTOCOL_BYTES = SEC_WEBSOCKET_PROTOCOL.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_VERSION_BYTES = SEC_WEBSOCKET_VERSION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_KEY_BYTES = SEC_WEBSOCKET_KEY.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_ACCEPT_BYTES = SEC_WEBSOCKET_ACCEPT.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SEC_WEBSOCKET_EXTENSIONS_BYTES = SEC_WEBSOCKET_EXTENSIONS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SERVER_BYTES = SERVER.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SET_COOKIE_BYTES = SET_COOKIE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] SET_COOKIE2_BYTES = SET_COOKIE2.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] TE_BYTES = TE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] TRAILER_BYTES = TRAILER.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] TRANSFER_ENCODING_BYTES = TRANSFER_ENCODING.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] UPGRADE_BYTES = UPGRADE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] UPGRADE_INSECURE_REQUESTS_BYTES = UPGRADE_INSECURE_REQUESTS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] USER_AGENT_BYTES = USER_AGENT.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] VARY_BYTES = VARY.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] VIA_BYTES = VIA.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] WARNING_BYTES = WARNING.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] WEBSOCKET_LOCATION_BYTES = WEBSOCKET_LOCATION.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] WEBSOCKET_ORIGIN_BYTES = WEBSOCKET_ORIGIN.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] WEBSOCKET_PROTOCOL_BYTES = WEBSOCKET_PROTOCOL.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] WWW_AUTHENTICATE_BYTES = WWW_AUTHENTICATE.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] X_FRAME_OPTIONS_BYTES = X_FRAME_OPTIONS.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] X_REQUESTED_WITH_BYTES = X_REQUESTED_WITH.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] ALT_SVC_BYTES = ALT_SVC.getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] NONE_BYTES = NONE.getBytes(StandardCharsets.ISO_8859_1);

    private static final Map<String, byte[]> SB_MAP = new HashMap<>();

    static {
        SB_MAP.put(ACCEPT, ACCEPT_BYTES);
        SB_MAP.put(ACCEPT_CHARSET, ACCEPT_CHARSET_BYTES);
        SB_MAP.put(ACCEPT_ENCODING, ACCEPT_ENCODING_BYTES);
        SB_MAP.put(ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_BYTES);
        SB_MAP.put(ACCEPT_RANGES, ACCEPT_RANGES_BYTES);
        SB_MAP.put(ACCEPT_PATCH, ACCEPT_PATCH_BYTES);
        SB_MAP.put(ACCESS_CONTROL_ALLOW_CREDENTIALS, ACCESS_CONTROL_ALLOW_CREDENTIALS_BYTES);
        SB_MAP.put(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS_BYTES);
        SB_MAP.put(ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS_BYTES);
        SB_MAP.put(ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN_BYTES);
        SB_MAP.put(ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK, ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK_BYTES);
        SB_MAP.put(ACCESS_CONTROL_EXPOSE_HEADERS, ACCESS_CONTROL_EXPOSE_HEADERS_BYTES);
        SB_MAP.put(ACCESS_CONTROL_MAX_AGE, ACCESS_CONTROL_MAX_AGE_BYTES);
        SB_MAP.put(ACCESS_CONTROL_REQUEST_HEADERS, ACCESS_CONTROL_REQUEST_HEADERS_BYTES);
        SB_MAP.put(ACCESS_CONTROL_REQUEST_METHOD, ACCESS_CONTROL_REQUEST_METHOD_BYTES);
        SB_MAP.put(ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK, ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK_BYTES);
        SB_MAP.put(AGE, AGE_BYTES);
        SB_MAP.put(ALLOW, ALLOW_BYTES);
        SB_MAP.put(AUTHORIZATION, AUTHORIZATION_BYTES);
        SB_MAP.put(CACHE_CONTROL, CACHE_CONTROL_BYTES);
        SB_MAP.put(CONNECTION, CONNECTION_BYTES);
        SB_MAP.put(CONTENT_BASE, CONTENT_BASE_BYTES);
        SB_MAP.put(CONTENT_ENCODING, CONTENT_ENCODING_BYTES);
        SB_MAP.put(CONTENT_LANGUAGE, CONTENT_LANGUAGE_BYTES);
        SB_MAP.put(CONTENT_LENGTH, CONTENT_LENGTH_BYTES);
        SB_MAP.put(CONTENT_LOCATION, CONTENT_LOCATION_BYTES);
        SB_MAP.put(CONTENT_TRANSFER_ENCODING, CONTENT_TRANSFER_ENCODING_BYTES);
        SB_MAP.put(CONTENT_DISPOSITION, CONTENT_DISPOSITION_BYTES);
        SB_MAP.put(CONTENT_MD5, CONTENT_MD5_BYTES);
        SB_MAP.put(CONTENT_RANGE, CONTENT_RANGE_BYTES);
        SB_MAP.put(CONTENT_SECURITY_POLICY, CONTENT_SECURITY_POLICY_BYTES);
        SB_MAP.put(CONTENT_TYPE, CONTENT_TYPE_BYTES);
        SB_MAP.put(COOKIE, COOKIE_BYTES);
        SB_MAP.put(DATE, DATE_BYTES);
        SB_MAP.put(DNT, DNT_BYTES);
        SB_MAP.put(ETAG, ETAG_BYTES);
        SB_MAP.put(EXPECT, EXPECT_BYTES);
        SB_MAP.put(EXPIRES, EXPIRES_BYTES);
        SB_MAP.put(FROM, FROM_BYTES);
        SB_MAP.put(HOST, HOST_BYTES);
        SB_MAP.put(IF_MATCH, IF_MATCH_BYTES);
        SB_MAP.put(IF_MODIFIED_SINCE, IF_MODIFIED_SINCE_BYTES);
        SB_MAP.put(IF_NONE_MATCH, IF_NONE_MATCH_BYTES);
        SB_MAP.put(IF_RANGE, IF_RANGE_BYTES);
        SB_MAP.put(IF_UNMODIFIED_SINCE, IF_UNMODIFIED_SINCE_BYTES);
        SB_MAP.put(KEEP_ALIVE, KEEP_ALIVE_BYTES);
        SB_MAP.put(LAST_MODIFIED, LAST_MODIFIED_BYTES);
        SB_MAP.put(LOCATION, LOCATION_BYTES);
        SB_MAP.put(MAX_FORWARDS, MAX_FORWARDS_BYTES);
        SB_MAP.put(ORIGIN, ORIGIN_BYTES);
        SB_MAP.put(PRAGMA, PRAGMA_BYTES);
        SB_MAP.put(PROXY_AUTHENTICATE, PROXY_AUTHENTICATE_BYTES);
        SB_MAP.put(PROXY_AUTHORIZATION, PROXY_AUTHORIZATION_BYTES);
        SB_MAP.put(PROXY_CONNECTION, PROXY_CONNECTION_BYTES);
        SB_MAP.put(RANGE, RANGE_BYTES);
        SB_MAP.put(REFERER, REFERER_BYTES);
        SB_MAP.put(RETRY_AFTER, RETRY_AFTER_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_KEY1, SEC_WEBSOCKET_KEY1_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_KEY2, SEC_WEBSOCKET_KEY2_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_LOCATION, SEC_WEBSOCKET_LOCATION_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_ORIGIN, SEC_WEBSOCKET_ORIGIN_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_PROTOCOL, SEC_WEBSOCKET_PROTOCOL_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_VERSION, SEC_WEBSOCKET_VERSION_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_KEY, SEC_WEBSOCKET_KEY_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_ACCEPT, SEC_WEBSOCKET_ACCEPT_BYTES);
        SB_MAP.put(SEC_WEBSOCKET_EXTENSIONS, SEC_WEBSOCKET_EXTENSIONS_BYTES);
        SB_MAP.put(SERVER, SERVER_BYTES);
        SB_MAP.put(SET_COOKIE, SET_COOKIE_BYTES);
        SB_MAP.put(SET_COOKIE2, SET_COOKIE2_BYTES);
        SB_MAP.put(TE, TE_BYTES);
        SB_MAP.put(TRAILER, TRAILER_BYTES);
        SB_MAP.put(TRANSFER_ENCODING, TRANSFER_ENCODING_BYTES);
        SB_MAP.put(UPGRADE, UPGRADE_BYTES);
        SB_MAP.put(UPGRADE_INSECURE_REQUESTS, UPGRADE_INSECURE_REQUESTS_BYTES);
        SB_MAP.put(USER_AGENT, USER_AGENT_BYTES);
        SB_MAP.put(VARY, VARY_BYTES);
        SB_MAP.put(VIA, VIA_BYTES);
        SB_MAP.put(WARNING, WARNING_BYTES);
        SB_MAP.put(WEBSOCKET_LOCATION, WEBSOCKET_LOCATION_BYTES);
        SB_MAP.put(WEBSOCKET_ORIGIN, WEBSOCKET_ORIGIN_BYTES);
        SB_MAP.put(WEBSOCKET_PROTOCOL, WEBSOCKET_PROTOCOL_BYTES);
        SB_MAP.put(WWW_AUTHENTICATE, WWW_AUTHENTICATE_BYTES);
        SB_MAP.put(X_FRAME_OPTIONS, X_FRAME_OPTIONS_BYTES);
        SB_MAP.put(X_REQUESTED_WITH, X_REQUESTED_WITH_BYTES);
        SB_MAP.put(ALT_SVC, ALT_SVC_BYTES);
        SB_MAP.put(NONE, NONE_BYTES);
    }

    public static byte[] bytesOf(String key) {
        return SB_MAP.getOrDefault(key, new byte[0]);
    }
}
