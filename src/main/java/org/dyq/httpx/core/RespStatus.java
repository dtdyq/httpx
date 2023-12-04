package org.dyq.httpx.core;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RespStatus {

    public static final int CONTINUE = 100;
    public static final int SWITCHING_PROTOCOLS = 101;
    public static final int PROCESSING = 102;
    public static final int EARLY_HINTS = 103;
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int NON_AUTHORITATIVE_INFORMATION = 203;
    public static final int NO_CONTENT = 204;
    public static final int RESET_CONTENT = 205;
    public static final int PARTIAL_CONTENT = 206;
    public static final int MULTI_STATUS = 207;
    public static final int ALREADY_REPORTED = 208;
    public static final int IM_USED = 226;
    public static final int MULTIPLE_CHOICES = 300;
    public static final int MOVED_PERMANENTLY = 301;
    public static final int FOUND = 302;
    public static final int SEE_OTHER = 303;
    public static final int NOT_MODIFIED = 304;
    public static final int USE_PROXY = 305;
    public static final int TEMPORARY_REDIRECT = 307;
    public static final int PERMANENT_REDIRECT = 308;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int PAYMENT_REQUIRED = 402;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int PROXY_AUTHENTICATION_REQUIRED = 407;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int CONFLICT = 409;
    public static final int GONE = 410;
    public static final int LENGTH_REQUIRED = 411;
    public static final int PRECONDITION_FAILED = 412;
    public static final int CONTENT_TOO_LARGE = 413;
    public static final int URI_TOO_LONG = 414;
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int RANGE_NOT_SATISFIABLE = 416;
    public static final int EXPECTATION_FAILED = 417;
    public static final int MISDIRECTED_REQUEST = 421;
    public static final int UNPROCESSABLE_CONTENT = 422;
    public static final int LOCKED = 423;
    public static final int FAILED_DEPENDENCY = 424;
    public static final int TOO_EARLY = 425;
    public static final int UPGRADE_REQUIRED = 426;
    public static final int PRECONDITION_REQUIRED = 428;
    public static final int TOO_MANY_REQUESTS = 429;
    public static final int REQUEST_HEADER_FIELDS_TOO_LARGE = 431;
    public static final int UNAVAILABLE_FOR_LEGAL_REASONS = 451;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_IMPLEMENTED = 501;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;
    public static final int HTTP_VERSION_NOT_SUPPORTED = 505;
    public static final int VARIANT_ALSO_NEGOTIATES = 506;
    public static final int INSUFFICIENT_STORAGE = 507;
    public static final int LOOP_DETECTED = 508;
    public static final int NOT_EXTENDED_OBSOLETED = 510;
    public static final int NETWORK_AUTHENTICATION_REQUIRED = 511;

    public static final byte[] CONTINUE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.CONTINUE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] SWITCHING_PROTOCOLS_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.SWITCHING_PROTOCOLS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PROCESSING_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.PROCESSING).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] EARLY_HINTS_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.EARLY_HINTS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] OK_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.OK).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CREATED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.CREATED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] ACCEPTED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.ACCEPTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NON_AUTHORITATIVE_INFORMATION_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NON_AUTHORITATIVE_INFORMATION).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NO_CONTENT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NO_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RESET_CONTENT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.RESET_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PARTIAL_CONTENT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.PARTIAL_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MULTI_STATUS_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.MULTI_STATUS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] ALREADY_REPORTED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.ALREADY_REPORTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] IM_USED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.IM_USED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MULTIPLE_CHOICES_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.MULTIPLE_CHOICES).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MOVED_PERMANENTLY_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.MOVED_PERMANENTLY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] FOUND_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.FOUND).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] SEE_OTHER_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.SEE_OTHER).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_MODIFIED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NOT_MODIFIED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] USE_PROXY_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.USE_PROXY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] TEMPORARY_REDIRECT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.TEMPORARY_REDIRECT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PERMANENT_REDIRECT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.PERMANENT_REDIRECT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] BAD_REQUEST_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNAUTHORIZED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.UNAUTHORIZED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PAYMENT_REQUIRED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.PAYMENT_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] FORBIDDEN_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.FORBIDDEN).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_FOUND_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NOT_FOUND).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] METHOD_NOT_ALLOWED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.METHOD_NOT_ALLOWED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_ACCEPTABLE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NOT_ACCEPTABLE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PROXY_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.PROXY_AUTHENTICATION_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] REQUEST_TIMEOUT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.REQUEST_TIMEOUT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONFLICT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.CONFLICT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] GONE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.GONE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] LENGTH_REQUIRED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.LENGTH_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PRECONDITION_FAILED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.PRECONDITION_FAILED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONTENT_TOO_LARGE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.CONTENT_TOO_LARGE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] URI_TOO_LONG_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.URI_TOO_LONG).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNSUPPORTED_MEDIA_TYPE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.UNSUPPORTED_MEDIA_TYPE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RANGE_NOT_SATISFIABLE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.RANGE_NOT_SATISFIABLE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] EXPECTATION_FAILED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.EXPECTATION_FAILED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MISDIRECTED_REQUEST_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.MISDIRECTED_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNPROCESSABLE_CONTENT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.UNPROCESSABLE_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] LOCKED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.LOCKED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] FAILED_DEPENDENCY_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.FAILED_DEPENDENCY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] TOO_EARLY_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.TOO_EARLY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UPGRADE_REQUIRED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.UPGRADE_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PRECONDITION_REQUIRED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.PRECONDITION_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] TOO_MANY_REQUESTS_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.TOO_MANY_REQUESTS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] REQUEST_HEADER_FIELDS_TOO_LARGE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.REQUEST_HEADER_FIELDS_TOO_LARGE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNAVAILABLE_FOR_LEGAL_REASONS_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.UNAVAILABLE_FOR_LEGAL_REASONS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] INTERNAL_SERVER_ERROR_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.INTERNAL_SERVER_ERROR).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_IMPLEMENTED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NOT_IMPLEMENTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] BAD_GATEWAY_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.BAD_GATEWAY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] SERVICE_UNAVAILABLE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.SERVICE_UNAVAILABLE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] GATEWAY_TIMEOUT_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.GATEWAY_TIMEOUT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] HTTP_VERSION_NOT_SUPPORTED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.HTTP_VERSION_NOT_SUPPORTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] VARIANT_ALSO_NEGOTIATES_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.VARIANT_ALSO_NEGOTIATES).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] INSUFFICIENT_STORAGE_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.INSUFFICIENT_STORAGE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] LOOP_DETECTED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.LOOP_DETECTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_EXTENDED_OBSOLETED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NOT_EXTENDED_OBSOLETED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NETWORK_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V1 = """
            HTTP/1.1 %d
            """.formatted(RespStatus.NETWORK_AUTHENTICATION_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONTINUE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.CONTINUE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] SWITCHING_PROTOCOLS_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.SWITCHING_PROTOCOLS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PROCESSING_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.PROCESSING).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] EARLY_HINTS_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.EARLY_HINTS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] OK_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.OK).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CREATED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.CREATED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] ACCEPTED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.ACCEPTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NON_AUTHORITATIVE_INFORMATION_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NON_AUTHORITATIVE_INFORMATION).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NO_CONTENT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NO_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RESET_CONTENT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.RESET_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PARTIAL_CONTENT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.PARTIAL_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MULTI_STATUS_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.MULTI_STATUS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] ALREADY_REPORTED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.ALREADY_REPORTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] IM_USED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.IM_USED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MULTIPLE_CHOICES_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.MULTIPLE_CHOICES).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MOVED_PERMANENTLY_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.MOVED_PERMANENTLY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] FOUND_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.FOUND).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] SEE_OTHER_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.SEE_OTHER).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_MODIFIED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NOT_MODIFIED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] USE_PROXY_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.USE_PROXY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] TEMPORARY_REDIRECT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.TEMPORARY_REDIRECT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PERMANENT_REDIRECT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.PERMANENT_REDIRECT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] BAD_REQUEST_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.BAD_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNAUTHORIZED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.UNAUTHORIZED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PAYMENT_REQUIRED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.PAYMENT_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] FORBIDDEN_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.FORBIDDEN).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_FOUND_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NOT_FOUND).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] METHOD_NOT_ALLOWED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.METHOD_NOT_ALLOWED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_ACCEPTABLE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NOT_ACCEPTABLE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PROXY_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.PROXY_AUTHENTICATION_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] REQUEST_TIMEOUT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.REQUEST_TIMEOUT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONFLICT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.CONFLICT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] GONE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.GONE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] LENGTH_REQUIRED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.LENGTH_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PRECONDITION_FAILED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.PRECONDITION_FAILED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONTENT_TOO_LARGE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.CONTENT_TOO_LARGE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] URI_TOO_LONG_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.URI_TOO_LONG).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNSUPPORTED_MEDIA_TYPE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.UNSUPPORTED_MEDIA_TYPE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RANGE_NOT_SATISFIABLE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.RANGE_NOT_SATISFIABLE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] EXPECTATION_FAILED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.EXPECTATION_FAILED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] MISDIRECTED_REQUEST_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.MISDIRECTED_REQUEST).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNPROCESSABLE_CONTENT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.UNPROCESSABLE_CONTENT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] LOCKED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.LOCKED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] FAILED_DEPENDENCY_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.FAILED_DEPENDENCY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] TOO_EARLY_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.TOO_EARLY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UPGRADE_REQUIRED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.UPGRADE_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PRECONDITION_REQUIRED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.PRECONDITION_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] TOO_MANY_REQUESTS_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.TOO_MANY_REQUESTS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] REQUEST_HEADER_FIELDS_TOO_LARGE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.REQUEST_HEADER_FIELDS_TOO_LARGE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] UNAVAILABLE_FOR_LEGAL_REASONS_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.UNAVAILABLE_FOR_LEGAL_REASONS).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] INTERNAL_SERVER_ERROR_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.INTERNAL_SERVER_ERROR).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_IMPLEMENTED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NOT_IMPLEMENTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] BAD_GATEWAY_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.BAD_GATEWAY).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] SERVICE_UNAVAILABLE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.SERVICE_UNAVAILABLE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] GATEWAY_TIMEOUT_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.GATEWAY_TIMEOUT).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] HTTP_VERSION_NOT_SUPPORTED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.HTTP_VERSION_NOT_SUPPORTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] VARIANT_ALSO_NEGOTIATES_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.VARIANT_ALSO_NEGOTIATES).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] INSUFFICIENT_STORAGE_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.INSUFFICIENT_STORAGE).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] LOOP_DETECTED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.LOOP_DETECTED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NOT_EXTENDED_OBSOLETED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NOT_EXTENDED_OBSOLETED).getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] NETWORK_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V0 = """
            HTTP/1.0 %d
            """.formatted(RespStatus.NETWORK_AUTHENTICATION_REQUIRED).getBytes(StandardCharsets.ISO_8859_1);


    private static final Map<Integer, byte[]> SB_MAP_V0 = new HashMap<>();
    private static final Map<Integer, byte[]> SB_MAP_V1 = new HashMap<>();

    public static byte[] v0RspBytes(int code) {
        return SB_MAP_V0.get(code);
    }

    public static byte[] v1RspBytes(int code) {
        return SB_MAP_V1.get(code);
    }

    static {
        SB_MAP_V0.put(CONTINUE, CONTINUE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(SWITCHING_PROTOCOLS, SWITCHING_PROTOCOLS_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(PROCESSING, PROCESSING_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(EARLY_HINTS, EARLY_HINTS_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(OK, OK_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(CREATED, CREATED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(ACCEPTED, ACCEPTED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NON_AUTHORITATIVE_INFORMATION, NON_AUTHORITATIVE_INFORMATION_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NO_CONTENT, NO_CONTENT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(RESET_CONTENT, RESET_CONTENT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(PARTIAL_CONTENT, PARTIAL_CONTENT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(MULTI_STATUS, MULTI_STATUS_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(ALREADY_REPORTED, ALREADY_REPORTED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(IM_USED, IM_USED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(MULTIPLE_CHOICES, MULTIPLE_CHOICES_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(MOVED_PERMANENTLY, MOVED_PERMANENTLY_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(FOUND, FOUND_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(SEE_OTHER, SEE_OTHER_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NOT_MODIFIED, NOT_MODIFIED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(USE_PROXY, USE_PROXY_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(TEMPORARY_REDIRECT, TEMPORARY_REDIRECT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(PERMANENT_REDIRECT, PERMANENT_REDIRECT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(BAD_REQUEST, BAD_REQUEST_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(UNAUTHORIZED, UNAUTHORIZED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(PAYMENT_REQUIRED, PAYMENT_REQUIRED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(FORBIDDEN, FORBIDDEN_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NOT_FOUND, NOT_FOUND_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(METHOD_NOT_ALLOWED, METHOD_NOT_ALLOWED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NOT_ACCEPTABLE, NOT_ACCEPTABLE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(PROXY_AUTHENTICATION_REQUIRED, PROXY_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(REQUEST_TIMEOUT, REQUEST_TIMEOUT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(CONFLICT, CONFLICT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(GONE, GONE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(LENGTH_REQUIRED, LENGTH_REQUIRED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(PRECONDITION_FAILED, PRECONDITION_FAILED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(CONTENT_TOO_LARGE, CONTENT_TOO_LARGE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(URI_TOO_LONG, URI_TOO_LONG_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(UNSUPPORTED_MEDIA_TYPE, UNSUPPORTED_MEDIA_TYPE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(RANGE_NOT_SATISFIABLE, RANGE_NOT_SATISFIABLE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(EXPECTATION_FAILED, EXPECTATION_FAILED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(MISDIRECTED_REQUEST, MISDIRECTED_REQUEST_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(UNPROCESSABLE_CONTENT, UNPROCESSABLE_CONTENT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(LOCKED, LOCKED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(FAILED_DEPENDENCY, FAILED_DEPENDENCY_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(TOO_EARLY, TOO_EARLY_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(UPGRADE_REQUIRED, UPGRADE_REQUIRED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(PRECONDITION_REQUIRED, PRECONDITION_REQUIRED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(TOO_MANY_REQUESTS, TOO_MANY_REQUESTS_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(REQUEST_HEADER_FIELDS_TOO_LARGE, REQUEST_HEADER_FIELDS_TOO_LARGE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(UNAVAILABLE_FOR_LEGAL_REASONS, UNAVAILABLE_FOR_LEGAL_REASONS_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NOT_IMPLEMENTED, NOT_IMPLEMENTED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(BAD_GATEWAY, BAD_GATEWAY_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(GATEWAY_TIMEOUT, GATEWAY_TIMEOUT_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(HTTP_VERSION_NOT_SUPPORTED, HTTP_VERSION_NOT_SUPPORTED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(VARIANT_ALSO_NEGOTIATES, VARIANT_ALSO_NEGOTIATES_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(INSUFFICIENT_STORAGE, INSUFFICIENT_STORAGE_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(LOOP_DETECTED, LOOP_DETECTED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NOT_EXTENDED_OBSOLETED, NOT_EXTENDED_OBSOLETED_RESP_LINE_BYTES_V0);
        SB_MAP_V0.put(NETWORK_AUTHENTICATION_REQUIRED, NETWORK_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V0);
        SB_MAP_V1.put(CONTINUE, CONTINUE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(SWITCHING_PROTOCOLS, SWITCHING_PROTOCOLS_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(PROCESSING, PROCESSING_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(EARLY_HINTS, EARLY_HINTS_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(OK, OK_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(CREATED, CREATED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(ACCEPTED, ACCEPTED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NON_AUTHORITATIVE_INFORMATION, NON_AUTHORITATIVE_INFORMATION_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NO_CONTENT, NO_CONTENT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(RESET_CONTENT, RESET_CONTENT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(PARTIAL_CONTENT, PARTIAL_CONTENT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(MULTI_STATUS, MULTI_STATUS_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(ALREADY_REPORTED, ALREADY_REPORTED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(IM_USED, IM_USED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(MULTIPLE_CHOICES, MULTIPLE_CHOICES_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(MOVED_PERMANENTLY, MOVED_PERMANENTLY_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(FOUND, FOUND_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(SEE_OTHER, SEE_OTHER_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NOT_MODIFIED, NOT_MODIFIED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(USE_PROXY, USE_PROXY_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(TEMPORARY_REDIRECT, TEMPORARY_REDIRECT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(PERMANENT_REDIRECT, PERMANENT_REDIRECT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(BAD_REQUEST, BAD_REQUEST_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(UNAUTHORIZED, UNAUTHORIZED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(PAYMENT_REQUIRED, PAYMENT_REQUIRED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(FORBIDDEN, FORBIDDEN_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NOT_FOUND, NOT_FOUND_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(METHOD_NOT_ALLOWED, METHOD_NOT_ALLOWED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NOT_ACCEPTABLE, NOT_ACCEPTABLE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(PROXY_AUTHENTICATION_REQUIRED, PROXY_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(REQUEST_TIMEOUT, REQUEST_TIMEOUT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(CONFLICT, CONFLICT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(GONE, GONE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(LENGTH_REQUIRED, LENGTH_REQUIRED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(PRECONDITION_FAILED, PRECONDITION_FAILED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(CONTENT_TOO_LARGE, CONTENT_TOO_LARGE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(URI_TOO_LONG, URI_TOO_LONG_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(UNSUPPORTED_MEDIA_TYPE, UNSUPPORTED_MEDIA_TYPE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(RANGE_NOT_SATISFIABLE, RANGE_NOT_SATISFIABLE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(EXPECTATION_FAILED, EXPECTATION_FAILED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(MISDIRECTED_REQUEST, MISDIRECTED_REQUEST_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(UNPROCESSABLE_CONTENT, UNPROCESSABLE_CONTENT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(LOCKED, LOCKED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(FAILED_DEPENDENCY, FAILED_DEPENDENCY_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(TOO_EARLY, TOO_EARLY_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(UPGRADE_REQUIRED, UPGRADE_REQUIRED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(PRECONDITION_REQUIRED, PRECONDITION_REQUIRED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(TOO_MANY_REQUESTS, TOO_MANY_REQUESTS_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(REQUEST_HEADER_FIELDS_TOO_LARGE, REQUEST_HEADER_FIELDS_TOO_LARGE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(UNAVAILABLE_FOR_LEGAL_REASONS, UNAVAILABLE_FOR_LEGAL_REASONS_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NOT_IMPLEMENTED, NOT_IMPLEMENTED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(BAD_GATEWAY, BAD_GATEWAY_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(GATEWAY_TIMEOUT, GATEWAY_TIMEOUT_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(HTTP_VERSION_NOT_SUPPORTED, HTTP_VERSION_NOT_SUPPORTED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(VARIANT_ALSO_NEGOTIATES, VARIANT_ALSO_NEGOTIATES_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(INSUFFICIENT_STORAGE, INSUFFICIENT_STORAGE_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(LOOP_DETECTED, LOOP_DETECTED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NOT_EXTENDED_OBSOLETED, NOT_EXTENDED_OBSOLETED_RESP_LINE_BYTES_V1);
        SB_MAP_V1.put(NETWORK_AUTHENTICATION_REQUIRED, NETWORK_AUTHENTICATION_REQUIRED_RESP_LINE_BYTES_V1);
    }
}
