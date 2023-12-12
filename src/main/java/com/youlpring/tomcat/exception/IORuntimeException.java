package com.youlpring.tomcat.exception;

public class IORuntimeException extends RuntimeException {

    public IORuntimeException(String message, Exception e) {
        super(message, e);
    }
}
