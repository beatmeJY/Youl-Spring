package com.youlpring.tomcat.apache.coyote.http11.context;

import com.youlpring.jws.common.config.SessionConfiguration;

import java.time.LocalDateTime;

public class Session {

    private final String sessionKey;
    private final LocalDateTime maxEffectiveTime;
    private final UserSessionInfo userInfo;

    public Session(String sessionKey, UserSessionInfo userInfo, LocalDateTime maxEffectiveTime) {
        this.sessionKey = sessionKey;
        this.userInfo = userInfo;
        this.maxEffectiveTime = maxEffectiveTime;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public UserSessionInfo getUserInfo() {
        return userInfo;
    }

    public LocalDateTime getMaxEffectiveTime() {
        return maxEffectiveTime;
    }
}
