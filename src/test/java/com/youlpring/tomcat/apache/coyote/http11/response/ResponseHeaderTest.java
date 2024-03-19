package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] ResponseHeader 테스트")
class ResponseHeaderTest {

    @Test
    @DisplayName("ResponseHeader 저장에 성공한다.")
    void addHeader() {
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.addHeader(HttpHeaderConstant.CONTENT_TYPE, ContentType.TEXT_HTML.getContentString());

        String expectedHeader = HttpHeaderConstant.CONTENT_TYPE + ": " + ContentType.TEXT_HTML.getContentString() + "\r\n";
        assertEquals(expectedHeader, responseHeader.getHeaderString());
    }
}
