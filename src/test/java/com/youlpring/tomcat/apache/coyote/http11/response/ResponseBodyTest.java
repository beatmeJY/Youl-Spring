package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit] ResponseBody 테스트")
class ResponseBodyTest {

    @Test
    @DisplayName("body 와 ContentType 에 저장에 성공한다.")
    public void responseBody() {
        final String body =  "body";
        ResponseBody responseBody = new ResponseBody(body, ContentType.TEXT_HTML);

        assertEquals(body, responseBody.getBody());
        assertEquals(4, responseBody.getBodyLength());
        assertEquals(ContentType.TEXT_HTML.getContentString(), responseBody.getContentTypeString());
    }
}
