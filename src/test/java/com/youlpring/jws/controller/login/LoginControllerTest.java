package com.youlpring.jws.controller.login;

import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.exception.LoginException;
import com.youlpring.jws.model.User;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

@DisplayName("[Unit] LoginController 테스트")
class LoginControllerTest {

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
        InMemoryUserRepository.save(new User(ACCOUNT_VALUE, PASSWORD_VALUE, EMAIL_KEY));
        try {
            HttpRequest mockRequest = mock(HttpRequest.class);
            HttpResponse mockResponse = mock(HttpResponse.class);
            when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenReturn(ACCOUNT_VALUE);
            when(mockRequest.getNotNullBodyValue(PASSWORD_KEY)).thenReturn(PASSWORD_VALUE);

            loginController.doPost(mockRequest, mockResponse);

            //TODO - 추후 세션데이터 구현 시 응답 객체의 유저정보와 비교문 추가
            verify(mockResponse).clientRedirect("/", true);
        } finally {
            InMemoryUserRepository.deleteUser(ACCOUNT_VALUE);
        }
    }

    @Test
    @DisplayName("존재하지 않는 사용자 로그인 요청에 실패한다.")
    void doPostFailByNotFoundUser() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenReturn(ACCOUNT_VALUE);
        when(mockRequest.getNotNullBodyValue(PASSWORD_KEY)).thenReturn(PASSWORD_VALUE);

        loginController.doPost(mockRequest, mockResponse);

        verify(mockResponse).setHttpStatus(HttpStatus.UNAUTHORIZED);
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
