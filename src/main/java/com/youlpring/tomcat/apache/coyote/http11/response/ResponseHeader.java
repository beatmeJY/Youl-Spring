package com.youlpring.tomcat.apache.coyote.http11.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResponseHeader {

    private static final Logger log = LoggerFactory.getLogger(ResponseHeader.class);

    private final Map<String, Object> headers = new HashMap<>();

    public ResponseHeader() {
    }

    public void setHeader(String headerKey, Object value) {
        headers.put(headerKey, value);
    }

    public String getHeaderString() {
        StringBuilder builder = new StringBuilder();
        Set<String> keys = headers.keySet();
        for (String key : keys) {
            builder.append(key);
            builder.append(": ");
            builder.append(headers.get(key)).append("\r\n");
        }
        return builder.toString();
    }
}
