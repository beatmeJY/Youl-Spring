package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.tomcat.apache.coyote.http11.constants.CookieConstant;
import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.context.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ResponseHeader {

    private static final Logger log = LoggerFactory.getLogger(ResponseHeader.class);

    private final Map<String, Object> headerMap = new HashMap<>();
    private final Map<String, Cookie> cookieMap = new HashMap<>();

    public ResponseHeader() {
    }

    public void addHeader(String headerKey, Object value) {
        headerMap.put(headerKey, value);
    }

    public void addCookie(Cookie cookie) {
        if (cookie == null || cookie.getName() == null) {
            return;
        }
        cookieMap.put(cookie.getName(), cookie);
    }

    public void expireSessionCookie() {
        Cookie expireCookie = new Cookie(CookieName.JSESSIONID, null);
        expireCookie.setMaxAge(0L);
        cookieMap.put(expireCookie.getName(), expireCookie);
    }

    public Object getHeader(String headerKey) {
        return headerMap.get(headerKey);
    }

    public Cookie getCookie(String cookieName) {
        return cookieMap.get(cookieName);
    }

    public String getHeaderString() {
        StringBuilder builder = new StringBuilder();
        headerMap.keySet().forEach(key -> toStringLine(builder, key, headerMap.get(key)));
        cookieMap.keySet().forEach(key -> toStringLine(builder, CookieConstant.SET_COOKIE, cookieMap.get(key)));
        return builder.toString();
    }

    private void toStringLine(StringBuilder builder, String key, Object value) {
        builder.append(key);
        builder.append(": ");
        builder.append(value.toString()).append("\r\n");
    }
}
