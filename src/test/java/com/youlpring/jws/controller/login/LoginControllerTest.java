package com.youlpring.jws.controller.login;

import com.youlpring.common.db.InitDbBase;
import com.youlpring.jws.common.config.CookieConfiguration;
import com.youlpring.jws.common.exception.LoginException;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.model.user.User;
import com.youlpring.tomcat.apache.coyote.http11.context.Cookie;
import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionManager;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.youlpring.Fixture.jws.user.UserFixture.*;
import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.ACCOUNT_KEY;
import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.PASSWORD_KEY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("[Unit] LoginController 테스트")
class LoginControllerTest extends InitDbBase {

    private final static LoginController loginController = LoginController.INSTANCE;

    @Test
    @DisplayName("GET 요청 시 로그인 페이지를 리턴한다.")
    void doGetSuccess() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        loginController.doGet(mockRequest, response);

        assertEquals("/login", response.getViewName());
    }

    @Test
    @DisplayName("로그인 요청에 성공한다.")
    void doPostSuccess() {
        SessionManager sessionManager = SessionManager.INSTANCE;
        InMemoryUserRepository.save(new User(ACCOUNT, PASSWORD, EMAIL));

        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenReturn(ACCOUNT);
        when(mockRequest.getNotNullBodyValue(PASSWORD_KEY)).thenReturn(PASSWORD);

        loginController.doPost(mockRequest, response);
        Cookie responseCookie = response.getResponseHeader().getCookie(CookieName.JSESSIONID.name());
        assertNotNull(sessionManager.findSession(responseCookie.getValue()));
        assertEquals(CookieConfiguration.DEFAULT_MAX_AGE, responseCookie.getMaxAge());
        assertEquals("/", response.getResponseBody().getBody());
    }

    @Test
    @DisplayName("존재하지 않는 사용자 로그인 요청에 실패한다.")
    void doPostFailByNotFoundUser() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenReturn(ACCOUNT);
        when(mockRequest.getNotNullBodyValue(PASSWORD_KEY)).thenReturn(PASSWORD);

        assertThrowsExactly(LoginException.class, () -> loginController.doPost(mockRequest, mockResponse));
    }

    @Test
    @DisplayName("올바르지 않는 파라미터의 로그인 요청에 실패한다.")
    void doPostFailByParamError() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenThrow(new IllegalArgumentException("요청 필수 값이 없습니다."));

        assertThrowsExactly(
                LoginException.class,
                () -> loginController.doPost(mockRequest, mockResponse),
                "로그인 입력 정보가 올바르지 않습니다.");
    }
}
