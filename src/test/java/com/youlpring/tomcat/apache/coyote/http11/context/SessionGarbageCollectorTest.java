package com.youlpring.tomcat.apache.coyote.http11.context;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("[Unit] SessionGarbageCollector 테스트")
class SessionGarbageCollectorTest {

    SessionManager sessionManager = SessionManager.INSTANCE;

    @Test
    @DisplayName("세션스토리지에 만료된 세션을 찾아 삭제에 성공한다.")
    void checkInvalidSessionSuccess() throws InterruptedException {
        SessionGarbageCollector sessionGarbageCollector = new SessionGarbageCollector(0, 100, TimeUnit.MILLISECONDS);
        Session session = new Session("key", Mockito.mock(UserSessionInfo.class), LocalDateTime.now().minusMinutes(1));
        sessionManager.add(session);

        Session findSession = sessionManager.findSession(session.getSessionKey());
        assertEquals(session, findSession);

        sessionGarbageCollector.processExpiredSessions();
        Thread.sleep(1000);

        assertNull(sessionManager.findSession(session.getSessionKey()));
    }
}