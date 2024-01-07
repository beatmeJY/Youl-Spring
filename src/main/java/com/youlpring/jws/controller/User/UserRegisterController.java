package com.youlpring.jws.controller.User;

import com.youlpring.jws.controller.AbstractController;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.exception.UserRegisterException;
import com.youlpring.jws.model.User;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class UserRegisterController extends AbstractController {

    public static final UserRegisterController INSTANCE = new UserRegisterController();

    private UserRegisterController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setViewName("/register.html");
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        User newUser = new User(
            request.getNotNullBodyValue("account"),
            request.getNotNullBodyValue("password"),
            request.getNotNullBodyValue("email")
        );
        if (InMemoryUserRepository.findByAccount(newUser.getAccount()) != null) {
            throw new UserRegisterException("이미 존재하는 계정입니다.");
        }
        InMemoryUserRepository.save(newUser);
        response.clientRedirect("/login", true);
    }
}
