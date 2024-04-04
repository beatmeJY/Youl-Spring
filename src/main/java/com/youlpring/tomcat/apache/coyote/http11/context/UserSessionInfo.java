package com.youlpring.tomcat.apache.coyote.http11.context;

public class UserSessionInfo {

    private final Long id;
    private final String account;
    private final String email;

    public UserSessionInfo(Long id, String account, String email) {
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
