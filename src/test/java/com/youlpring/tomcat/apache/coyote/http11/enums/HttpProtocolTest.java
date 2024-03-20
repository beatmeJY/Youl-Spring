package com.youlpring.tomcat.apache.coyote.http11.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit] HttpProtocol 테스트")
class HttpProtocolTest {

    @Test
    @DisplayName("알맞는 Protocol 를 반환한다.")
    void valueOfString() {
        assertEquals(HttpProtocol.HTTP_1_1, HttpProtocol.valueOfString("HTTP/1.1"));
        assertEquals(HttpProtocol.HTTP_2, HttpProtocol.valueOfString("HTTP/2"));
        assertEquals(HttpProtocol.HTTP_3, HttpProtocol.valueOfString("HTTP/3"));
    }
    
    @Test
    @DisplayName("알 수 없는 Protocol 은 HTTP_1_1로 반환한다.")
    void notFoundValueOfString() {
        assertEquals(HttpProtocol.HTTP_1_1, HttpProtocol.valueOfString("HTTP/TEST"));
    }
}
