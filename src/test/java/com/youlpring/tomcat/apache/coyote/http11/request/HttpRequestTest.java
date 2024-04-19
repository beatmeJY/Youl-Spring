package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.Fixture.common.TestConfigFixture;
import com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture;
import com.youlpring.jws.common.exception.SessionExpirationException;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.context.Session;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionManager;
import com.youlpring.tomcat.apache.coyote.http11.context.UserSessionInfo;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpMethod;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("[Unit] HttpRequest 테스트")
class HttpRequestTest {

    @Test
    @DisplayName("Protocol 을 성공적으로 저장한다.")
    void getProtocolSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest(RequestFixture.requestAll());

        assertEquals(HttpProtocol.HTTP_1_1, httpRequest.getProtocol());

    }
    @Test
    @DisplayName("Method 를 성공적으로 저장한다.")
    void getMethodSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest(RequestFixture.requestAll());

        assertEquals(HttpMethod.GET, httpRequest.getMethod());
    }

    @Test
    @DisplayName("URL 을 성공적으로 저장한다.")
    void getUrlSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest(RequestFixture.requestAll());

        assertEquals(RequestFixture.HOME_REQUEST_URL, httpRequest.getUrl());
    }

    @Test
    @DisplayName("Header 정보를 성공적으로 저장한다.")
    void getHeaderValueSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest(RequestFixture.requestAll());

        assertEquals(TestConfigFixture.TEST_SERVER_IP_PORT, httpRequest.getHeaderValue(HttpHeaderConstant.HOST));
    }

    @Test
    @DisplayName("Body 정보를 성공적으로 저장한다.")
    void getBodyValueSuccess() throws IOException {
        HttpRequest httpRequest = saveHttpRequest(RequestFixture.requestAll());

        for (String key : RequestFixture.bodyMap.keySet()) {
            assertEquals(RequestFixture.bodyMap.get(key), httpRequest.getBodyValue(key));
        }
    }

    @Test
    @DisplayName("쿠키에 JSESSIONID 가 있다면 실제 Session 을 가져온다.")
    void initSessionSuccess() throws IOException {
        HttpRequest request = saveHttpRequest(RequestFixture.requestAll());
        SessionManager sessionManager = SessionManager.INSTANCE;
        sessionManager.add(new Session(RequestFixture.JSSESIONID_VALUE, mock(UserSessionInfo.class), LocalDateTime.now().plusMinutes(10)));

        request.initSession();

        assertEquals(sessionManager.findSession(RequestFixture.JSSESIONID_VALUE), request.getSession());
        assertTrue(request.isLogin());
    }
    
    @Test
    @DisplayName("쿠키에 JSESSIONID 값이 있더라도 SessionStorage 에 없다면 세션만료 예외를 발생시킨다.")
    void initSession() throws IOException {
        HttpRequest request = saveHttpRequest(RequestFixture.requestAll());

        assertThrowsExactly(SessionExpirationException.class, request::initSession);
    }

    private HttpRequest saveHttpRequest(String headers) throws IOException {
        Socket mockSocket = mock(Socket.class);

        ByteArrayInputStream inputStreamFixture = new ByteArrayInputStream(headers.getBytes());
        when(mockSocket.getInputStream()).thenReturn(inputStreamFixture);

        return new HttpRequest(new BufferedReader(new InputStreamReader(mockSocket.getInputStream())));
    }
}
