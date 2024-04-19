package com.youlpring.jws.controller.login;

import com.youlpring.Fixture.jws.user.UserFixture;
import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.context.Session;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionManager;
import com.youlpring.tomcat.apache.coyote.http11.context.UserSessionInfo;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit] SessionReloadController 테스트")
class SessionReloadControllerTest {

    private final static SessionReloadController sessionReloadController = SessionReloadController.INSTANCE;

    private final static SessionManager sessionManager = SessionManager.INSTANCE;

    @Test
    @DisplayName("세션 리로드에 성공한다.")
    void sessionReloadSuccess() {
        HttpRequest mockRequest = Mockito.mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        Session oldSession = sessionManager.createSession(new UserSessionInfo(1L, UserFixture.ACCOUNT, UserFixture.USER.getEmail()));
        sessionManager.add(oldSession);

        Mockito.when(mockRequest.getSession()).thenReturn(oldSession);
        sessionReloadController.doGet(mockRequest, response);

        assertNull(sessionManager.findSession(oldSession.getSessionKey()));
        assertNotEquals(oldSession.getSessionKey(), response.getCookie(CookieName.JSESSIONID.name()).getValue());
        assertNotNull(sessionManager.findSession(response.getCookie(CookieName.JSESSIONID.name()).getValue()));
    }
}