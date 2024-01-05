package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;

public interface Controller {

    void service(HttpRequest request, HttpResponse httpResponse);
}
