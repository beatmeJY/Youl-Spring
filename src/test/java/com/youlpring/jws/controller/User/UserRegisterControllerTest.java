package com.youlpring.jws.controller.User;

import com.youlpring.common.db.InitDbBase;
import com.youlpring.jws.common.exception.UserRegisterException;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.youlpring.Fixture.jws.user.UserFixture.*;
import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

@DisplayName("[Unit] UserRegisterController 테스트")
class UserRegisterControllerTest extends InitDbBase {

    public static final UserRegisterController userRegisterController  = UserRegisterController.INSTANCE;


    @Test
    @DisplayName("GET 요청 시 회원가입 페이지를 리턴한다.")
    public void doGetSuccess() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();

        userRegisterController.doGet(mockRequest, response);

        assertEquals("/register", response.getViewName());
    }

    @Test
    @DisplayName("회원가입 요청에 성공한다.")
    void doPostSuccess() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenReturn(ACCOUNT);
        when(mockRequest.getNotNullBodyValue(PASSWORD_KEY)).thenReturn(PASSWORD);
        when(mockRequest.getNotNullBodyValue(EMAIL_KEY)).thenReturn(EMAIL);

        userRegisterController.doPost(mockRequest, mockResponse);

        assertEquals(ACCOUNT, InMemoryUserRepository.findByAccount(ACCOUNT).getAccount());
        verify(mockResponse).clientRedirect("/login");
    }

    @Test
    @DisplayName("이미 존재하는 유저는 가입에 실패한다.")
    void doPostFailByExisted() {
        InMemoryUserRepository.save(USER);

        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenReturn(ACCOUNT);
        when(mockRequest.getNotNullBodyValue(PASSWORD_KEY)).thenReturn(PASSWORD);

        assertThrowsExactly(
                UserRegisterException.class,
                () -> userRegisterController.doPost(mockRequest, mockResponse),
                "이미 존재하는 계정입니다.");
    }

    @Test
    @DisplayName("올바르지 않는 파라미터의 회원가입에 실패한다.")
    void doPostFailByParamError() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockRequest.getNotNullBodyValue(ACCOUNT_KEY)).thenThrow(new IllegalArgumentException());

        assertThrowsExactly(
                UserRegisterException.class,
                () -> userRegisterController.doPost(mockRequest, mockResponse),
                "회원 저장 시 올바르지 않는 파라미터 값입니다.");
    }
}
