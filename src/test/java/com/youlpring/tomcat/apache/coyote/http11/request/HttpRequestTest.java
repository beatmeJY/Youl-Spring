package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.Fixture.common.TestConfigFixture;
import com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpMethod;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@DisplayName("[Unit] HttpRequest 테스트")
class HttpRequestTest {

    @Test
    @DisplayName("Protocol 을 성공적으로 저장한다.")
    void getProtocolSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest();

        Assertions.assertEquals(HttpProtocol.HTTP_1_1, httpRequest.getProtocol());

    }
    @Test
    @DisplayName("Method 를 성공적으로 저장한다.")
    void getMethodSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest();

        Assertions.assertEquals(HttpMethod.GET, httpRequest.getMethod());
    }

    @Test
    @DisplayName("URL 을 성공적으로 저장한다.")
    void getUrlSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest();

        Assertions.assertEquals(RequestFixture.HOME_REQUEST_URL, httpRequest.getUrl());
    }

    @Test
    @DisplayName("Header 정보를 성공적으로 저장한다.")
    void getHeaderValueSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest();

        Assertions.assertEquals(TestConfigFixture.TEST_SERVER_IP_PORT, httpRequest.getHeaderValue(HttpHeaderConstant.HOST));
    }

    @Test
    @DisplayName("Body 정보를 성공적으로 저장한다.")
    void getBodyValueSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest();

        for (String key : RequestFixture.bodyMap.keySet()) {
            Assertions.assertEquals(RequestFixture.bodyMap.get(key), httpRequest.getBodyValue(key));
        }
    }

    private HttpRequest saveHttpRequest() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);

        ByteArrayInputStream inputStreamFixture = new ByteArrayInputStream(RequestFixture.requestAll().getBytes());
        Mockito.when(mockSocket.getInputStream()).thenReturn(inputStreamFixture);

        return new HttpRequest(new BufferedReader(new InputStreamReader(mockSocket.getInputStream())));
    }
}
