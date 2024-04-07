package com.youlpring.jws.common.exception;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import com.youlpring.tomcat.apache.coyote.http11.response.ResponseHeader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] ExceptionHandlerTest 테스트")
class ExceptionHandlerTest {

    @Test
    @DisplayName("CodeAndMessage 를 사용받은 예외 캐치에 성공한다")
    void CodeAndMessageCatch() {
        HttpResponse response = new HttpResponse();
        ExceptionHandler.exceptionHandling(new LoginException(ErrorCodeAndMessage.FAILED_LOGIN), response);

        assertEquals(ErrorCodeAndMessage.FAILED_LOGIN.getHttpStatus(), response.getHttpStatus());
        assertEquals(ErrorCodeAndMessage.FAILED_LOGIN.getMessage(), response.getResponseBody().getBody());
    }

    @Test
    @DisplayName("유저 세션 만료시 쿠키를 삭제하며 로그인페이지로 이동한다.")
    void SessionExpirationExceptionCatch() {
        HttpResponse response = new HttpResponse();
        ExceptionHandler.exceptionHandling(new SessionExpirationException(ErrorCodeAndMessage.SESSION_EXPIRATION), response);

        ResponseHeader responseHeader = response.getResponseHeader();
        assertEquals("/login", responseHeader.getHeader(HttpHeaderConstant.LOCATION));
        assertEquals(0L, responseHeader.getCookie(CookieName.JSESSIONID.name()).getMaxAge());
        assertEquals(HttpStatus.FOUND, response.getHttpStatus());
    }


    @Test
    @DisplayName("컨트롤하지 못한 예외는 INTERNAL_SERVER_ERROR 처리한다.")
    void InternalServerError() {
        HttpResponse response = new HttpResponse();
        ExceptionHandler.exceptionHandling(new IllegalArgumentException(), response);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getHttpStatus());
    }
}
