package com.youlpring.jws.controller.login;

import com.youlpring.jws.controller.AbstractController;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionManager;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class SessionReloadController extends AbstractController {

    public static final SessionReloadController INSTANCE = new SessionReloadController();

    private static final SessionManager sessionManager = SessionManager.INSTANCE;

    private SessionReloadController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.createSessionCookie(sessionManager.sessionRefresh(request.getSession()).getSessionKey());
    }
}
