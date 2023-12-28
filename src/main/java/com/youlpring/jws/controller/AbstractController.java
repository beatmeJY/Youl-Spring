package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public abstract class AbstractController implements Controller {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        switch (request.getMethod()) {
            case GET -> doGet(request, response);
            case POST -> doPost(request, response);
            case PATCH -> doPatch(request, response);
            case PUT -> doPut(request, response);
            case DELETE -> doDelete(request, response);
            default -> throw new IllegalArgumentException("HTTP method 정보가 올바르지 않습니다.");
        }
    }

    public void doGet(HttpRequest request, HttpResponse response) {}
    public void doPost(HttpRequest request, HttpResponse response) {}
    public void doPatch(HttpRequest request, HttpResponse response) {}
    public void doPut(HttpRequest request, HttpResponse response) {}
    public void doDelete(HttpRequest request, HttpResponse response) {}
}
