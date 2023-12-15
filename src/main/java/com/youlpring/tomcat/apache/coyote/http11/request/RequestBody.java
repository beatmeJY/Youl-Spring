package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.tomcat.apache.util.IOUtil;

import java.io.BufferedReader;

public class RequestBody {

    private String body;

    public RequestBody(BufferedReader bufferedReader) {
        if (!IOUtil.ready(bufferedReader)) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = IOUtil.readLine(bufferedReader)) != null) {
            builder.append(line);
        }
        body = builder.toString();
    }
}
