package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.enums.HttpMethod;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.HOME_REQUEST_URL;
import static com.youlpring.Fixture.tomcat.coyote.http11.ResponseFixture.getExpectedHomePageNotLoggedIn;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("[Unit] FrontController 테스트")
class FrontControllerTest {

    @Test
    @DisplayName("컨트롤러를 통해 동적 요청을 처리한다.")
    void processSuccess() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        when(mockRequest.getUrl()).thenReturn(HOME_REQUEST_URL);
        when(mockRequest.getMethod()).thenReturn(HttpMethod.GET);

        FrontController.process(mockRequest, response);

        assertArrayEquals(getExpectedHomePageNotLoggedIn().getBytes(), response.getHttpByte());
    }
}
