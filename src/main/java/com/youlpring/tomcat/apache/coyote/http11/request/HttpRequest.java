package com.youlpring.tomcat.apache.coyote.http11.request;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;
import com.youlpring.jws.common.exception.UserException;
import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.context.Session;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionManager;
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

    private static final SessionManager sessionManager = SessionManager.INSTANCE;
    private HttpMethod method;
    private String url;
    private Map<String, Object> queryParameterMap;
    private HttpProtocol protocol;
    private Session session;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    public HttpRequest(BufferedReader bufferedReader) throws IOException {
        String line;
        if ((line = IOUtil.readLine(bufferedReader)) == null) {
            throw new IOException("HTTP 요청이 올바르지 않습니다.");
        }
        saveFirstHeader(line);
        this.requestHeader = new RequestHeader(bufferedReader);
        saveBody(bufferedReader);
    }

    private void saveBody(BufferedReader bufferedReader) {
        String headerValue = this.getHeaderValue(HttpHeaderConstant.CONTENT_LENGTH);
        if (headerValue == null) {
            return;
        }
        try {
            this.requestBody = new RequestBody(bufferedReader, Integer.parseInt(headerValue));
        } catch (NumberFormatException e) {
            throw new HttpMessageException("HTTP 요청 Content-Length 정보가 올바르지 않습니다.");
        }
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
            createQueryParameter(split[1]);
        }
    }

    private void createQueryParameter(String parameters) {
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

    public void initSession() {
        String sessionKey = getCookie(CookieName.JSESSIONID.name());
        if (sessionKey == null || sessionKey.isBlank()) {
            return;
        }
        Session findSession = sessionManager.findSession(sessionKey);
        if (findSession == null || sessionManager.isTimeOver(findSession)) {
            throw new UserException(ErrorCodeAndMessage.UNAUTHORIZED);
        }
        this.session = findSession;
    }

    public Session getSession() {
        if (session == null) {
            throw new UserException(ErrorCodeAndMessage.UNAUTHORIZED);
        }
        return session;
    }

    public boolean isLogin() {
        return session != null;
    }

    public void deleteSession() {
        sessionManager.remove(session);
        session = null;
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

    public String getHeaderValue(String key) {
        if (requestHeader == null) {
            throw new HttpMessageException("HTTP 헤더가 존재 하지 않습니다.");
        }
        return requestHeader.getHeaderValue(key);
    }

    public String getBodyValue(String key) {
        if (requestBody == null) {
            return null;
        }
        return requestBody.getParam(key);
    }

    public String getCookie(String cookieName) {
        return requestHeader.getCookieValue(cookieName);
    }

    public String getNotNullBodyValue(String key) {
        if (requestBody == null || requestBody.getParam(key) == null) {
            throw new IllegalArgumentException("요청 필수 값이 없습니다.");
        }
        return requestBody.getParam(key);
    }
}
