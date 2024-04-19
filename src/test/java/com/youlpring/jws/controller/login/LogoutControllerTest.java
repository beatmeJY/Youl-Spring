package com.youlpring.jws.controller.login;

import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.context.Session;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionManager;
import com.youlpring.tomcat.apache.coyote.http11.context.UserSessionInfo;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import com.youlpring.tomcat.apache.coyote.http11.response.ResponseHeader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("[Unit] LogoutController 테스트")
class LogoutControllerTest {

    private final static LogoutController logoutController = LogoutController.INSTANCE;
    private final static SessionManager sessionManager = SessionManager.INSTANCE;

    @Test
    @DisplayName("로그아웃에 성공한다.")
    void logoutSuccess() {
        Session session = sessionManager.createSession(mock(UserSessionInfo.class));
        sessionManager.add(session);
        HttpRequest mockRequest = mock(HttpRequest.class);
        when(mockRequest.getSession()).thenReturn(session);
        HttpResponse response = new HttpResponse();

        logoutController.doGet(mockRequest, response);

        ResponseHeader responseHeader = response.getResponseHeader();
        assertEquals(0L, responseHeader.getCookie(CookieName.JSESSIONID.name()).getMaxAge());
        assertNull(sessionManager.findSession(session.getSessionKey()));
    }
}
