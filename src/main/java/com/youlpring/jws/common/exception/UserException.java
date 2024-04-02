package com.youlpring.jws.common.exception;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;

public class UserException extends CodeAndMessageException {

    public UserException(ErrorCodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
