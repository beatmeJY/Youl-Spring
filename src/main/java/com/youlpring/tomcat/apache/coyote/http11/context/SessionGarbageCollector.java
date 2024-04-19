package com.youlpring.tomcat.apache.coyote.http11.context;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SessionGarbageCollector {

    private final long initialDelay;

    private final long period;

    private final TimeUnit timeUnit;

    private final SessionManager sessionManager = SessionManager.INSTANCE;

    public SessionGarbageCollector(long initialDelay, long period, TimeUnit timeUnit) {
        this.initialDelay = initialDelay;
        this.period = period;
        this.timeUnit = timeUnit;
    }

    public void processExpiredSessions() {
        List<String> sessionKeys = sessionManager.getSessionKeys();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            for (String key : sessionKeys) {
                Session session = sessionManager.findSession(key);
                if (session == null) continue;
                sessionManager.isTimeOver(session);
            }
        };
        scheduler.scheduleAtFixedRate(task, initialDelay, period, timeUnit);
    }
}
