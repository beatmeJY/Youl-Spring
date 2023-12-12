package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpResponse {

    private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);

    private HttpProtocol httpProtocol = HttpProtocol.HTTP_1_1;
    private HttpStatus httpStatus = HttpStatus.OK;
    private ResponseHeader responseHeader;
    private ResponseBody responseBody;

    public HttpResponse(ResponseBody responseBody) throws IOException {
        this.responseHeader = new ResponseHeader();
        if (responseBody == null || responseBody.getBodyLength() == 0) {
            return;
        }
        this.responseBody = responseBody;
        responseHeader.setHeader(HttpHeaderConstant.CONTENT_LENGTH, responseBody.getBodyLength());
        if (responseBody.getContentType() != null) {
            responseHeader.setHeader(HttpHeaderConstant.CONTENT_TYPE, responseBody.getContentType());
        }
    }

    private String getFirstHeader() {
        return httpProtocol.getProtocol() + " " + httpStatus.getHeaderString();
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public byte[] getHttpByte() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getFirstHeader()).append("\r\n");
        builder.append(this.responseHeader.getHeaderString()).append("\r\n");
        builder.append("\r\n");
        if (responseBody != null && responseBody.getBodyLength() != 0) {
            builder.append(responseBody.getBody());
        }
        return builder.toString().getBytes();
    }
}
