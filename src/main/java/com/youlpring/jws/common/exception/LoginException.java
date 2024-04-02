package com.youlpring.jws.common.exception;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;

public class LoginException extends CodeAndMessageException {

    public LoginException(ErrorCodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
