package com.youlpring.tomcat.apache.coyote.http11.enums;

public enum HttpStatus {
    OK(200),
    FOUND(302),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500);

    private final int statusCode;

    HttpStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHeaderString() {
        return this.statusCode + " " + this.name();
    }
}
