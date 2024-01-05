package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class DefaultController extends AbstractController {

    public static final DefaultController INSTANCE = new DefaultController();

    private DefaultController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setViewName("/index");
    }
}
