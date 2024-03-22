package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.HOME_REQUEST_URL;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("[Unit] HomeController 테스트")
class HomeControllerTest {

    private final static HomeController homeController = HomeController.INSTANCE;

    @Test
    @DisplayName("컨트롤러를 통해 동적 요청을 처리한다.")
    void processSuccess() {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);

        homeController.doGet(mockRequest, mockResponse);

        verify(mockResponse).setViewName(HOME_REQUEST_URL);
    }
}
