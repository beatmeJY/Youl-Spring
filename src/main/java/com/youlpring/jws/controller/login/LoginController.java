package com.youlpring.jws.controller.login;

import com.youlpring.jws.controller.AbstractController;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.exception.LoginException;
import com.youlpring.jws.model.User;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpStatus;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class LoginController extends AbstractController {

    public static final LoginController INSTANCE = new LoginController();

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
                response.setHttpStatus(HttpStatus.UNAUTHORIZED);
                return;
            }
        } catch (IllegalArgumentException e) {
            throw new LoginException("로그인 입력 정보가 올바르지 않습니다.");
        }
        response.setClientRedirect("/", true);
    }
}
