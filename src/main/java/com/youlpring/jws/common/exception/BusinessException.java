package com.youlpring.jws.common.exception;

import com.youlpring.jws.common.codeAndMessage.CodeAndMessage;

public class BusinessException extends RuntimeException {

    CodeAndMessage codeAndMessage;

    public BusinessException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage.getMessage());
        this.codeAndMessage = codeAndMessage;
    }
}
