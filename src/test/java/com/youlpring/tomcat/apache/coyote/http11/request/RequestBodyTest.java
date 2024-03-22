package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] RequestBody 테스트")
class RequestBodyTest {

    @Test
    @DisplayName("요청에서 body 를 저장하는데 성공한다.")
    void getParamSuccess() throws IOException {
        RequestBody requestBody = saveRequestBody();

        assertEquals(3, RequestFixture.bodyMap.size());
        assertEquals(3, RequestFixture.bodyMap.keySet().size());

        for (String key : RequestFixture.bodyMap.keySet()) {
            assertEquals(RequestFixture.bodyMap.get(key), requestBody.getParam(key));
        }
    }

    @Test
    @DisplayName("RequestBody 의 keySet 을 얻는데 성공한다.")
    void getParamKeysSuccess() throws IOException {
        RequestBody requestBody = saveRequestBody();

        assertEquals(3, requestBody.getParamKeys().size());

        for (String paramKey : requestBody.getParamKeys()) {
            assertEquals(RequestFixture.bodyMap.get(paramKey), requestBody.getParam(paramKey));
        }
    }

    private RequestBody saveRequestBody() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(RequestFixture.body().getBytes());
        Mockito.when(mockSocket.getInputStream()).thenReturn(byteArrayInputStream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mockSocket.getInputStream()));

        return new RequestBody(bufferedReader, RequestFixture.body().getBytes().length);
    }
}
