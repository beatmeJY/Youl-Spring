package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.jws.controller.ModelAndView;
import com.youlpring.tomcat.apache.coyote.http11.context.CookieName;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.context.Cookie;
import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResponse {

    private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);

    private HttpProtocol httpProtocol = HttpProtocol.HTTP_1_1;
    private HttpStatus httpStatus = HttpStatus.OK;
    private final ResponseHeader responseHeader = new ResponseHeader();
    private ResponseBody responseBody;
    private final ModelAndView modelAndView = new ModelAndView();

    public HttpResponse() {}

    public void createResponseBody(String body, ContentType contentType) {
        if (body == null || body.isBlank()) {
            return;
        }
        this.responseBody = new ResponseBody(body, contentType);
        responseHeader.addHeader(HttpHeaderConstant.CONTENT_LENGTH, this.responseBody.getBodyLength());
        if (this.responseBody.getContentTypeString() != null) {
            responseHeader.addHeader(HttpHeaderConstant.CONTENT_TYPE, this.responseBody.getContentTypeString());
        }
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        if (httpStatus != null) {
            this.httpStatus = httpStatus;
        }
    }

    public void serverRedirect(String url) {
        setHttpStatus(HttpStatus.FOUND);
        responseHeader.addHeader("Location", url);
    }

    public void clientRedirect(String url) {
        setHttpStatus(HttpStatus.OK);
        createResponseBody(url, ContentType.TEXT_PLAIN);
    }

    public void addModel(String key, Object value) {
        modelAndView.addModel(key, value);
    }

    public void setViewName(String viewName) {
        modelAndView.setViewName(viewName);
    }

    public void createSessionCookie(String sessionKey) {
        Cookie cookie = new Cookie(CookieName.JSESSIONID, sessionKey);
        addCookie(cookie);
    }

    public void addCookie(Cookie cookie) {
       responseHeader.addCookie(cookie);
    }

    public void expireSessionCookie() {
        responseHeader.expireSessionCookie();
    }

    private String getFirstHeader() {
        return httpProtocol.getProtocol() + " " + httpStatus.getHeaderString();
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }

    public String getViewName() {
        return modelAndView.getViewName();
    }

    public byte[] getHttpByte() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getFirstHeader()).append("\r\n");
        builder.append(this.responseHeader.getHeaderString()).append("\r\n");
        if (responseBody != null && responseBody.getBodyLength() != 0) {
            builder.append(responseBody.getBody());
        }
        return builder.toString().getBytes();
    }
}
