package com.youlpring.tomcat.apache.coyote.http11;

import com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture;
import com.youlpring.Fixture.tomcat.coyote.http11.ResponseFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("[Unit] Http11Processor 테스트")
class Http11ProcessorTest {

    @Test
    @DisplayName("TXT 정적 파일 요청에 성공한다.")
    void staticFileProcessSuccess() throws IOException {
        OutputStream mockOutputStream = new ByteArrayOutputStream();
        callByApi(mockOutputStream, RequestFixture.STATIC_FILE_REQUEST_URL);

        assertEquals(ResponseFixture.getExpectedStaticFile(), mockOutputStream.toString());
    }

    @Test
    @DisplayName("HTML 요청은 동적요청으로 성공한다.")
    void htmlFileNotStatic() throws IOException {
        OutputStream mockOutputStream = new ByteArrayOutputStream();
        callByApi(mockOutputStream, RequestFixture.HOME_REQUEST_URL);

        assertEquals(ResponseFixture.getExpectedHomePageNotLoggedIn(), mockOutputStream.toString());
    }

    private void callByApi(OutputStream outputStream, String url) throws IOException {
        Socket mockSocket = mock(Socket.class);
        InputStream inputStream = new ByteArrayInputStream(RequestFixture.firstHeader(url).getBytes());

        when(mockSocket.getInputStream()).thenReturn(inputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        Http11Processor http11Processor = new Http11Processor(mockSocket);
        http11Processor.run();
    }
}
