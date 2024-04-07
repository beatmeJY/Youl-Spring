package com.youlpring.jws.controller;

import com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.youlpring.Fixture.tomcat.coyote.http11.ResponseFixture.getExpectedHomePage;
import static com.youlpring.Fixture.tomcat.coyote.http11.ResponseFixture.getHttpResponseOnlyFirstHeader;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("[Unit] ViewResolver 테스트")
class ViewResolverTest {

    @Test
    @DisplayName("타임리프를 통해 홈페이지 반환에 성공한다.")
    void executeSuccess() {
        HttpRequest request = Mockito.mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        response.setViewName(RequestFixture.HOME_REQUEST_URL);

        ViewResolver.execute(request, response);

        assertArrayEquals(getExpectedHomePage().getBytes(), response.getHttpByte());
    }

    @Test
    @DisplayName("ViewName 이 없으면 뷰는 반환하지 않는다.")
    void executeFail() {
        HttpRequest request = Mockito.mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();

        ViewResolver.execute(request, response);

        assertArrayEquals(getHttpResponseOnlyFirstHeader().getBytes(), response.getHttpByte());
    }
}
