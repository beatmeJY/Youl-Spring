package com.youlpring.jws.common.exception;

import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class ExceptionHandler {

    public static void exceptionHandling(Exception e, HttpResponse response) {
        if (e instanceof UserRegisterException) {
            response.serverRedirect("/register");
            return;
        }
        if (e instanceof UserException) {
            response.serverRedirect("/home");
            response.expireSessionCookie();
            return;
        }
        if (e instanceof CodeAndMessageException) {
            response.setHttpStatus(((CodeAndMessageException) e).codeAndMessage.getHttpStatus());
            response.createResponseBody(e.getMessage(), ContentType.TEXT_PLAIN);
            return;
        }
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
