package com.youlpring.jws.common.codeAndMessage;

import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;

public enum ErrorCodeAndMessage implements CodeAndMessage {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "올바르지 않는 양식입니다."),
    ALREADY_EXISTED_USER(HttpStatus.CONFLICT, "이미 존재하는 계정입니다."),
    FAILED_LOGIN(HttpStatus.UNAUTHORIZED, "로그인에 실패하였습니다."),
    SESSION_EXPIRATION(HttpStatus.UNAUTHORIZED, "세션이 만료 되었습니다.");
    
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCodeAndMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
