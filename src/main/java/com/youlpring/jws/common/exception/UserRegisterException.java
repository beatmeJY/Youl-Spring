package com.youlpring.jws.common.exception;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;

public class UserRegisterException extends CodeAndMessageException {

    public UserRegisterException(ErrorCodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
