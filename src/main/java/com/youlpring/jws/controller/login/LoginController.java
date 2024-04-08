package com.youlpring.jws.controller.login;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;
import com.youlpring.jws.controller.AbstractController;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.common.exception.LoginException;
import com.youlpring.jws.model.user.User;
import com.youlpring.tomcat.apache.coyote.http11.context.*;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class LoginController extends AbstractController {

    public static final LoginController INSTANCE = new LoginController();

    private static final SessionManager sessionManager = SessionManager.INSTANCE;

    private LoginController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setViewName("/login");
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            User findUser = InMemoryUserRepository.findByAccount(request.getNotNullBodyValue("account"));
            if (findUser == null || !findUser.checkPassword(request.getNotNullBodyValue("password"))) {
                throw new LoginException(ErrorCodeAndMessage.FAILED_LOGIN);
            }
            Session newSession = sessionManager.createSession(
                    new UserSessionInfo(
                        findUser.getId(),
                        findUser.getAccount(),
                        findUser.getEmail()));
            sessionManager.add(newSession);
            response.createSessionCookie(newSession.getSessionKey());
            response.clientRedirect("/");
        } catch (IllegalArgumentException e) {
            throw new LoginException(ErrorCodeAndMessage.FAILED_LOGIN);
        }
    }
}
