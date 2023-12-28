package com.youlpring.jws.controller;

import com.youlpring.jws.exception.UserRegisterException;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FrontController {

    private static final Logger log = LoggerFactory.getLogger(FrontController.class);

    private FrontController() {}

    public static void process(HttpRequest request, HttpResponse response) {
        try {
            log.info("요청 URL: {}", request.getUrl());
            getHandler(request).service(request, response);
            if (response.getViewName() != null && !"".equals(response.getViewName())) {
                ViewResolver.execute(response);
            }
        } catch (UserRegisterException e) {
            log.error("회원저장 오류", e);
            response.setClientRedirect("/register", false);
        }
        
    }

    private static Controller getHandler(HttpRequest request) {
        Controller controller = RequestHandlerMapping.getController(request.getUrl());
        if (controller == null) {
            return NotFoundController.INSTANCE;
        }
        return controller;
    }


}
