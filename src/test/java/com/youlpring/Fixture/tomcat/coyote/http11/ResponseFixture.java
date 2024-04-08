package com.youlpring.Fixture.tomcat.coyote.http11;

import com.youlpring.Fixture.utill.FileFixture;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;

public final class ResponseFixture {

    private ResponseFixture() {
        throw new AssertionError("올바른 방식의 객체를 생성해주세요");
    }

    public final static String CARRIAGE = "\r\n";

    public static String getHttpResponseOnlyFirstHeader() {
        return HttpProtocol.HTTP_1_1.getProtocol() + " " + HttpStatus.OK.getHeaderString() + CARRIAGE+CARRIAGE;
    }

    public static String getHttpResponseHeader(int length, ContentType contentType) {
        return HttpProtocol.HTTP_1_1.getProtocol() + " " + HttpStatus.OK.getHeaderString() + CARRIAGE
                + HttpHeaderConstant.CONTENT_LENGTH + ": " + length + CARRIAGE
                + HttpHeaderConstant.CONTENT_TYPE + ": " + contentType.getContentString() + CARRIAGE + CARRIAGE;
    }

    public static String getExpectedStaticFile() {
        String expectedHeader = ResponseFixture.getHttpResponseHeader(FileFixture.readStaticTextFile().length(), ContentType.TEXT_PLAIN);
        String expectedBody = FileFixture.readStaticTextFile();
        return expectedHeader + expectedBody;
    }

    public static String getExpectedHomePageLoggedIn() {
        String expectedHeader = getHttpResponseHeader(FileFixture.readDynamicFileLoggedIn().getBytes().length, ContentType.TEXT_HTML);
        String expectedBody = FileFixture.readDynamicFileNotLoggedIn();
        return expectedHeader + expectedBody;
    }

    public static String getExpectedHomePageNotLoggedIn() {
        String expectedHeader = getHttpResponseHeader(FileFixture.readDynamicFileNotLoggedIn().getBytes().length, ContentType.TEXT_HTML);
        String expectedBody = FileFixture.readDynamicFileNotLoggedIn();
        return expectedHeader + expectedBody;
    }
}
