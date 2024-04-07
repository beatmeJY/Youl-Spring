package com.youlpring.tomcat.apache.coyote.http11.context;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;
import com.youlpring.jws.common.exception.LoginException;
import com.youlpring.jws.common.exception.SessionExpirationException;

public class UserSessionInfo {

    private final Long id;
    private final String account;
    private final String email;

    public UserSessionInfo(Long id, String account, String email) {
        if (id == null || account == null || email == null) {
            throw new LoginException(ErrorCodeAndMessage.FAILED_LOGIN);
        }
        this.id = id;
        this.account = account;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }
}
