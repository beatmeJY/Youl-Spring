package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public class NotFoundController extends AbstractController {

    public static final NotFoundController INSTANCE = new NotFoundController();

    private NotFoundController() {}

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setServerRedirect("/");
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        response.setServerRedirect("/");
    }

    @Override
    public void doPatch(HttpRequest request, HttpResponse response) {
        response.setServerRedirect("/");
    }

    @Override
    public void doPut(HttpRequest request, HttpResponse response) {
        response.setServerRedirect("/");
    }

    @Override
    public void doDelete(HttpRequest request, HttpResponse response) {
        response.setServerRedirect("/");
    }
}
