package com.youlpring.tomcat.apache.coyote.http11.context;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;
import com.youlpring.jws.common.exception.LoginException;
import com.youlpring.tomcat.apache.catalina.Manager;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager implements Manager {

    public static final SessionManager INSTANCE = new SessionManager();

    private SessionManager() {}

    private final Map<String, Session> sessionStorage = new ConcurrentHashMap<>();
    private static final long MILLISECOND_BY_DAY = 86_400_000L;
    private static final String SESSION_RANDOM_STRING = "023456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ";


    public void add(Session session) {
        sessionStorage.put(session.getSessionKey(), session);
    }

    @Override
    public Session findSession(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return sessionStorage.get(id);
    }

    @Override
    public void remove(Session session) {
        sessionStorage.remove(session.getSessionKey());
    }

    public Session createSession(UserSessionInfo userSessionInfo) {
        if (userSessionInfo == null) {
            throw new LoginException(ErrorCodeAndMessage.FAILED_LOGIN);
        }
        return new Session(createSessionId(), userSessionInfo);
    }

    public boolean isTimeOver(Session session) {
        if (session.getMaxEffectiveTime().isBefore(LocalDateTime.now())) {
            remove(session);
            return true;
        }
        return false;
    }

    private String createSessionId() {
        SecureRandom random = new SecureRandom();
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("%08d", System.currentTimeMillis() % MILLISECOND_BY_DAY));
        for (int i = 0; i < 24; i++) {
            buffer.append(SESSION_RANDOM_STRING.charAt(random.nextInt(SESSION_RANDOM_STRING.length())));
        }
        return buffer.toString();
    }
}
