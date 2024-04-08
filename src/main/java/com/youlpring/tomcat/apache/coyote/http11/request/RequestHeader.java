package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.tomcat.apache.coyote.http11.constants.CookieConstant;
import com.youlpring.tomcat.apache.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHeader {

    private static final Logger log = LoggerFactory.getLogger(RequestHeader.class);

    private final Map<String, String> headerMap = new HashMap<>();
    private final Map<String, String> cookieMap = new HashMap<>();

    public RequestHeader(BufferedReader bufferedReader) {
        String line;
        while ((line = IOUtil.readLine(bufferedReader)) != null && !line.isBlank()) {
            String[] header = line.split(": ");
            if (header.length == 2) {
                headerMap.put(header[0], header[1]);
            }
        }
        if (headerMap.get(CookieConstant.COOKIE) != null) {
            setCookie();
        }
    }

    private void setCookie() {
        String cookies = headerMap.get(CookieConstant.COOKIE);
        String[] split = cookies.split("; ");
        for (String cookie : split) {
            setCookie(cookie);
        }
    }

    private void setCookie(String cookie) {
        String[] cookieKeyValue = cookie.split("=");
        if (cookieKeyValue.length == 2) {
            cookieMap.put(cookieKeyValue[0], cookieKeyValue[1]);
        }
    }

    public List<String> getHeaderKeys() {
        return new ArrayList<>(headerMap.keySet());
    }

    public String getHeaderValue(String key) {
        return headerMap.get(key);
    }

    public List<String> getCookieKeys() {
        return new ArrayList<>(cookieMap.keySet());
    }

    public String getCookieValue(String key) {
        return cookieMap.get(key);
    }
}
