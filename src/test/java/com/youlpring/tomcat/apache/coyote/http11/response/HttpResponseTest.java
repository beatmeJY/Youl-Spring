package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.Fixture.utill.FileFixture;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] HttpResponse 테스트")
class HttpResponseTest {

    private final static String MODEL_KEY = "user";
    private final static String MODEL_VALUE = "Youl";

    @Test
    @DisplayName("HttpResponse Model 과 View 저장에 성공한다.")
    void getModelAndView() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.addModel(MODEL_KEY, MODEL_VALUE);
        httpResponse.setViewName(FileFixture.DYNAMIC_URL);

        assertEquals(MODEL_VALUE, httpResponse.getModelAndView().getModelValue(MODEL_KEY));
        assertEquals(FileFixture.DYNAMIC_URL, httpResponse.getViewName());
    }

    @Test
    @DisplayName("HttpResponse 를 Byte 로 응답할 준비에 성공한다.")
    void getHttpByte() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.createResponseBody(FileFixture.readStaticTextFile(), ContentType.TEXT_PLAIN);

        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(HttpProtocol.HTTP_1_1.getProtocol()).append(" ").append(HttpStatus.OK.getHeaderString()).append("\r\n");
        expectedBuilder.append(HttpHeaderConstant.CONTENT_LENGTH).append(": ").append(FileFixture.readStaticTextFile().length()).append("\r\n");
        expectedBuilder.append(HttpHeaderConstant.CONTENT_TYPE).append(": ").append(ContentType.TEXT_PLAIN.getContentString()).append("\r\n\r\n");
        expectedBuilder.append(FileFixture.readStaticTextFile());
        byte[] expectedResponse = expectedBuilder.toString().getBytes();

        assertArrayEquals(expectedResponse, httpResponse.getHttpByte());
    }
}
