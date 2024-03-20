package com.youlpring.Fixture.tomcat.coyote.http11;

import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;

public final class ResponseFixture {

    private ResponseFixture() {}

    public static String getHttpResponseHeader(int length, ContentType contentType) {
        return HttpProtocol.HTTP_1_1.getProtocol() + " " + HttpStatus.OK.getHeaderString() + "\r\n"
                + HttpHeaderConstant.CONTENT_LENGTH + ": " + length + "\r\n"
                + HttpHeaderConstant.CONTENT_TYPE + ": " + contentType.getContentString() + "\r\n\r\n";
    }
}
