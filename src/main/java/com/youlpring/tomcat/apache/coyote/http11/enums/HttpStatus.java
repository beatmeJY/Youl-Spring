package com.youlpring.tomcat.apache.coyote.http11.enums;

public enum HttpStatus {
    OK(200),
    FOUND(302),
    UNAUTHORIZED(401),
    NOT_FOUND(404);

    private final int statusCode;

    HttpStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHeaderString() {
        return this.statusCode + " " + this.name();
    }
}
