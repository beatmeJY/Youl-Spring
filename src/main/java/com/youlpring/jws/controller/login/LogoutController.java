package com.youlpring.jws.controller.login;

import com.youlpring.jws.controller.AbstractController;
import com.youlpring.tomcat.apache.coyote.http11.context.SessionManager;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class LogoutController extends AbstractController {

    public static final LogoutController INSTANCE = new LogoutController();
    private static final SessionManager sessionManager = SessionManager.INSTANCE;

    private LogoutController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        sessionManager.delete(request, response);
        response.clientRedirect("/home");
    }
}
