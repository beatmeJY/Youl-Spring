package com.youlpring.tomcat.apache.coyote.http11.request;

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

    public RequestHeader(BufferedReader bufferedReader) {
        String line;
        while ((line = IOUtil.readLine(bufferedReader)) != null && !line.isBlank()) {
            String[] header = line.split(": ");
            headerMap.put(header[0], header[1]);
        }
    }

    public String getHeaderValue(String key) {
        return headerMap.get(key);
    }

    public List<String> getHeaderKeys() {
        return new ArrayList<>(headerMap.keySet());
    }
}
