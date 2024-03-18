package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.Fixture.common.TestConfigFixture;
import com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] RequestHeader 테스트")
class RequestHeaderTest {

    @Test
    @DisplayName("요청에서 Header 정보들을 저장하는데 성공한다.")
    void RequestHeaderSuccess() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);

        RequestHeader requestHeader = saveRequestHeader(mockSocket, RequestFixture.header());

        assertEquals(HttpHeaderConstant.HOST, requestHeader.getHeaderKeys().get(0));
        assertEquals(TestConfigFixture.TEST_SERVER_IP_PORT, requestHeader.getHeaderValue(requestHeader.getHeaderKeys().get(0)));
    }

    @Test
    @DisplayName("규격에 맞지않는 Header 정보는 건너뛴다.")
    void RequestHeaderNoSave() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);

        RequestHeader requestHeader = saveRequestHeader(mockSocket, "틀린:규격");

        assertEquals(0, requestHeader.getHeaderKeys().size());
    }

    private RequestHeader saveRequestHeader(Socket mockSocket, String headers) throws IOException {
        ByteArrayInputStream inputStreamFixture = new ByteArrayInputStream(headers.getBytes());
        Mockito.when(mockSocket.getInputStream()).thenReturn(inputStreamFixture);
        return new RequestHeader(new BufferedReader(new InputStreamReader(mockSocket.getInputStream())));
    }
}
