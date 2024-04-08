package com.youlpring.jws.controller.User;

import com.youlpring.jws.common.codeAndMessage.ErrorCodeAndMessage;
import com.youlpring.jws.controller.AbstractController;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.common.exception.UserRegisterException;
import com.youlpring.jws.model.user.User;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class UserRegisterController extends AbstractController {

    public static final UserRegisterController INSTANCE = new UserRegisterController();

    private UserRegisterController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setViewName("/register");
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            User newUser = new User(
                    request.getNotNullBodyValue("account"),
                    request.getNotNullBodyValue("password"),
                    request.getNotNullBodyValue("email"));
            if (InMemoryUserRepository.findByAccount(newUser.getAccount()) != null) {
                throw new UserRegisterException(ErrorCodeAndMessage.ALREADY_EXISTED_USER);
            }
            InMemoryUserRepository.save(newUser);
            response.clientRedirect("/login");
        } catch (IllegalArgumentException e) {
            throw new UserRegisterException(ErrorCodeAndMessage.BAD_REQUEST);
        }
    }
}
