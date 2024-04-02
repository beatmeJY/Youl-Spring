package com.youlpring.jws.common.exception;

import com.youlpring.jws.common.codeAndMessage.CodeAndMessage;

public class CodeAndMessageException extends RuntimeException {

    CodeAndMessage codeAndMessage;

    public CodeAndMessageException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage.getMessage());
        this.codeAndMessage = codeAndMessage;
    }
}
