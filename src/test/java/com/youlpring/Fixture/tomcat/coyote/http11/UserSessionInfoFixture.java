package com.youlpring.Fixture.tomcat.coyote.http11;

import com.youlpring.tomcat.apache.coyote.http11.context.UserSessionInfo;

public class UserSessionInfoFixture {

    public static final String ACCOUNT = "test";

    public static final String EMAIL = "test@gmail.com";

    public static final UserSessionInfo USER_SESSION_INFO = new UserSessionInfo(1L, ACCOUNT, EMAIL);

}
