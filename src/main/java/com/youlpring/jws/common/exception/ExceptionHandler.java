package com.youlpring.jws.common.exception;

import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class ExceptionHandler {

    public static void exceptionHandling(Exception exception, HttpResponse response) {
        if (exception instanceof SessionExpirationException) {
            response.serverRedirect("/login");
            response.expireSessionCookie();
            return;
        }
        if (exception instanceof BusinessException) {
            response.setHttpStatus(((BusinessException) exception).codeAndMessage.getHttpStatus());
            response.createResponseBody(exception.getMessage(), ContentType.TEXT_PLAIN);
            return;
        }
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
