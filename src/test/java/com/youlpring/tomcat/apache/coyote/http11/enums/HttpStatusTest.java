package com.youlpring.tomcat.apache.coyote.http11.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit] HttpStatus 테스트")
class HttpStatusTest {

    @Test
    @DisplayName("HttpStatus을 헤더 문자열로 반환한다.")
    void getHeaderString() {
        assertEquals("200 OK", HttpStatus.OK.getHeaderString());
    }
}
