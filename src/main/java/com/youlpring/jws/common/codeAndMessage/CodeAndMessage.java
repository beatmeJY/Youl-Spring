package com.youlpring.jws.common.codeAndMessage;

import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;

public interface CodeAndMessage {
    HttpStatus getHttpStatus();
    String getMessage();
}
