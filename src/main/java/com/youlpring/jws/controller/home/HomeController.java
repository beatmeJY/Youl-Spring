package com.youlpring.jws.controller.home;

import com.youlpring.jws.controller.AbstractController;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class HomeController extends AbstractController {

    public static final HomeController INSTANCE = new HomeController();

    private HomeController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setViewName("/index");
    }
}
