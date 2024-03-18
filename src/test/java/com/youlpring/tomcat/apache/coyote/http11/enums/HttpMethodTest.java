package com.youlpring.tomcat.apache.coyote.http11.enums;

import com.youlpring.tomcat.exception.HttpMessageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


@DisplayName("[Unit] HttpMethod 테스트")
class HttpMethodTest {

    @Test
    @DisplayName("알맞는 Method를 반환한다.")
    void valueOfMethod() {
        assertEquals(HttpMethod.POST, HttpMethod.valueOfMethod(HttpMethod.POST.name()));
    }
    
    @Test
    @DisplayName("알수 없는 Method 정보가 오면 에러로 응답한다.")
    void notFoundValueOfMethod() {
        assertThrowsExactly(HttpMessageException.class, () -> HttpMethod.valueOfMethod("TEST"), "HTTP 요청 정보가 올바르지 않습니다.");
    }
}
