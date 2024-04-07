package com.youlpring.tomcat.apache.coyote.http11.context;

import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("[Unit] SessionManager 테스트")
class SessionManagerTest {

    private final static SessionManager sessionManager = SessionManager.INSTANCE;

    @Test
    @DisplayName("세션을 생성하는데 성공한다.")
    void createSessionSuccess() {
        UserSessionInfo mockUserSessionInfo = mock(UserSessionInfo.class);

        Session session = sessionManager.createSession(mockUserSessionInfo);

        assertDoesNotThrow(() -> Long.parseLong(session.getSessionKey().substring(0, 8)));
        assertEquals(32, session.getSessionKey().length());
    }

    @Test
    @DisplayName("세선 저장에 성공한다.")
    void addSuccess() {
        UserSessionInfo mockUserSessionInfo = mock(UserSessionInfo.class);
        Session session = sessionManager.createSession(mockUserSessionInfo);

        sessionManager.add(session);

        Session findSession = sessionManager.findSession(session.getSessionKey());
        assertEquals(findSession.getSessionKey(), session.getSessionKey());
        assertEquals(findSession.getUserInfo(), session.getUserInfo());
        assertEquals(findSession.getMaxEffectiveTime(), session.getMaxEffectiveTime());
    }

    @Test
    @DisplayName("유효하지 않는 세션을 저장할 경우 저장에 실패한다.")
    void addFail() {
        assertThrowsExactly(IllegalArgumentException.class, () -> sessionManager.add(null), "세션 값이 올바르지 않습니다.");
        assertNull(sessionManager.findSession(null));
    }

    @Test
    @DisplayName("세션 삭제에 성공한다.")
    void removeSuccess() {
        UserSessionInfo mockUserSessionInfo = mock(UserSessionInfo.class);
        Session session = sessionManager.createSession(mockUserSessionInfo);
        sessionManager.add(session);

        sessionManager.remove(session);

        assertNull(sessionManager.findSession(session.getSessionKey()));
    }

    @Test
    @DisplayName("세션을 삭제할때 HTTP 객체도 삭제를 반영하는데 성공한다.")
    void deleteSuccess() {
        UserSessionInfo mockUserSessionInfo = mock(UserSessionInfo.class);
        Session session = sessionManager.createSession(mockUserSessionInfo);
        sessionManager.add(session);
        HttpRequest mockHttpRequest = mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        when(mockHttpRequest.getSession()).thenReturn(session);

        sessionManager.delete(mockHttpRequest, response);

        verify(mockHttpRequest).deleteSession();
        assertNull(sessionManager.findSession(session.getSessionKey()));
        assertEquals(0L, response.getResponseHeader().getCookie(CookieName.JSESSIONID.name()).getMaxAge());
    }

    @Test
    @DisplayName("세션이 만료시간을 경과하지 않았다면 성공한다.")
    void isTimeOverSuccess() {
        UserSessionInfo mockUserSessionInfo = mock(UserSessionInfo.class);
        Session session = sessionManager.createSession(mockUserSessionInfo);
        sessionManager.add(session);

        assertFalse(sessionManager.isTimeOver(session));
    }

    @Test
    @DisplayName("세션이 만료시간을 경과하였다면 세션 삭제에 성공한다.")
    void isTimeOverDeleteSuccess() {
        UserSessionInfo mockUserSessionInfo = mock(UserSessionInfo.class);
        Session session = sessionManager.createSession(mockUserSessionInfo);
        sessionManager.add(session);
        Session mockSession = mock(Session.class);
        when(mockSession.getSessionKey()).thenReturn(session.getSessionKey());
        when(mockSession.getMaxEffectiveTime()).thenReturn(LocalDateTime.now());

        assertTrue(sessionManager.isTimeOver(mockSession));
        assertNull(sessionManager.findSession(session.getSessionKey()));
    }
}
