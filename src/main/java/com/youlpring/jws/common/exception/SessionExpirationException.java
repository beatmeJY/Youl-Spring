package com.youlpring.jws.common.exception;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;

public class SessionExpirationException extends BusinessException {

    public SessionExpirationException(ErrorCodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
