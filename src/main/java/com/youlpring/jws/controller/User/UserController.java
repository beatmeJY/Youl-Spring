package com.youlpring.jws.controller.User;

import com.youlpring.jws.controller.AbstractController;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class UserController extends AbstractController {

    public static final UserController INSTANCE = new UserController();

    private UserController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        InMemoryUserRepository.findByAccount(request.getBodyValue("account"));
    }
}
