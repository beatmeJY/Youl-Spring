package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;

public class ResponseBody {

    private final String body;
    private final ContentType contentType;

    public ResponseBody(String body, ContentType contentType) {
        this.body = body;
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public String getContentTypeString() {
        if (contentType == null) {
            return null;
        }
        return contentType.getContentString();
    }

    public int getBodyLength() {
        if (body == null) {
            return 0;
        }
        return body.getBytes().length;
    }
}
