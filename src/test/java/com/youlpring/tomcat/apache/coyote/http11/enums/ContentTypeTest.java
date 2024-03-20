package com.youlpring.tomcat.apache.coyote.http11.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] ContentType 테스트")
class ContentTypeTest {

    @Test
    @DisplayName("ContentType 문자열 반환에 성공한다.")
    void getContentString() {
        assertEquals("application/javascript; charset=utf-8", ContentType.APPLICATION_JAVASCRIPT.getContentString());
    }
}
