package com.youlpring.common.core;

import com.youlpring.jws.common.config.SessionConfiguration;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionGarbageCollector;

import java.util.concurrent.TimeUnit;

public class DefaultInitializer implements Initializer {

    public void init() {
        sessionInit();
    }

    private void sessionInit() {
        SessionGarbageCollector sessionGarbageCollector = new SessionGarbageCollector(
                60 * SessionConfiguration.SESSION_MAX_EFFECTIVE_MINUTES, 60, TimeUnit.SECONDS);
        sessionGarbageCollector.processExpiredSessions();
    }
}
