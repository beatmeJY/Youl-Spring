package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.tomcat.apache.coyote.http11.enums.HttpMethod;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import com.youlpring.tomcat.apache.util.IOUtil;
import com.youlpring.tomcat.exception.HttpMessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

    private HttpMethod method;
    private String url;
    private Map<String, Object> queryParameterMap;
    private HttpProtocol protocol;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    public HttpRequest(BufferedReader bufferedReader) throws IOException {
        String line;
        if ((line = IOUtil.readLine(bufferedReader)) == null) {
            throw new IOException("HTTP 요청이 올바르지 않습니다.");
        }
        saveFirstHeader(line);
        this.requestHeader = new RequestHeader(bufferedReader);
        this.requestBody = new RequestBody(bufferedReader);
    }

    private void saveFirstHeader(String firstHeader) {
        String[] list = firstHeader.split(" ");
        if (list.length != 3) {
            throw new HttpMessageException("HTTP 요청 정보가 올바르지 않습니다.");
        }
        method = HttpMethod.valueOfMethod(list[0]);
        saveUri(list[1]);
        protocol = HttpProtocol.valueOfString(list[2]);
    }

    private void saveUri(String uri) {
        if (uri == null) {
            return;
        }
        String[] split = uri.split("\\?");
        url = split[0];
        if (split.length > 1) {
            setQueryParameter(split[1]);
        }
    }

    private void setQueryParameter(String parameters) {
        String[] parameterArray = parameters.split("&");
        if (parameterArray.length < 1) {
            return;
        }
        queryParameterMap = new HashMap<>();
        for (String parameter : parameterArray) {
            String[] query = parameter.split("=");
            queryParameterMap.put(query[0], query[1]);
        }
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public HttpProtocol getProtocol() {
        return protocol;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

}
