package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.tomcat.apache.util.IOUtil;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestBody {

    private String body;
    private final Map<String, String> paramMap = new HashMap<>();

    public RequestBody(BufferedReader bufferedReader, int contentLength) {
        if (!IOUtil.ready(bufferedReader)) {
            return;
        }
        char[] bodyChars = IOUtil.readByLength(bufferedReader, contentLength);
        body = String.valueOf(bodyChars);
        if (body != null) {
            createParamMap();
        }
    }

    private void createParamMap() {
        String[] params = body.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                paramMap.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public String getParam(String key) {
        return paramMap.get(key);
    }

    public List<String> getParamKey() {
        return new ArrayList<>(paramMap.keySet());
    }
}
