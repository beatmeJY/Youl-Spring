package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.jws.common.config.CookieConfiguration;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.context.Cookie;
import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("[Unit] ResponseHeader 테스트")
class ResponseHeaderTest {

    @Test
    @DisplayName("Header 저장에 성공한다.")
    void addHeader() {
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.addHeader(HttpHeaderConstant.CONTENT_TYPE, ContentType.TEXT_HTML.getContentString());

        String expectedHeader = HttpHeaderConstant.CONTENT_TYPE + ": " + ContentType.TEXT_HTML.getContentString() + "\r\n";
        assertEquals(expectedHeader, responseHeader.getHeaderString());
    }

    @Test
    @DisplayName("Cookie 저장에 성공한다.")
    void addCookie() {
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.addCookie(new Cookie(CookieName.JSESSIONID, "test"));

        Cookie cookie = responseHeader.getCookie(CookieName.JSESSIONID.name());
        assertNotNull(cookie);
        assertEquals(CookieConfiguration.DEFAULT_MAX_AGE, cookie.getMaxAge());
        assertEquals(CookieConfiguration.DEFAULT_PATH, cookie.getPath());
    }
}
