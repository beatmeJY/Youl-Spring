package com.youlpring.jws.controller;

import com.youlpring.jws.controller.User.UserController;
import com.youlpring.jws.controller.User.UserRegisterController;
import com.youlpring.jws.controller.login.LoginController;

import java.util.HashMap;
import java.util.Map;

public class RequestHandlerMapping {

    private final static Map<String, Controller> controllerMap = new HashMap<>();

    static {
        saveControllerMap();
    }

    private RequestHandlerMapping() {}

    private static void saveControllerMap() {
        controllerMap.put("/", HomeController.INSTANCE);
        controllerMap.put("/login", LoginController.INSTANCE);
        controllerMap.put("/user", UserController.INSTANCE);
        controllerMap.put("/register", UserRegisterController.INSTANCE);
    }

    public static Controller getController(String url) {
        return controllerMap.getOrDefault(url, DefaultController.INSTANCE);
    }
}
