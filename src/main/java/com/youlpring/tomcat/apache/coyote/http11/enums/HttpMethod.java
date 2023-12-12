package com.youlpring.tomcat.apache.coyote.http11.enums;

import com.youlpring.tomcat.exception.HttpMessageException;

public enum HttpMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE;

    public static HttpMethod valueOfMethod(String method) {
        try {
            return HttpMethod.valueOf(method);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new HttpMessageException("HTTP 요청 정보가 올바르지 않습니다.");
        }
    }
}
