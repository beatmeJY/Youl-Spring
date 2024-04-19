package com.youlpring.jws.controller;

import com.youlpring.jws.controller.User.UserController;
import com.youlpring.jws.controller.User.UserRegisterController;
import com.youlpring.jws.controller.home.HomeController;
import com.youlpring.jws.controller.login.LogoutController;
import com.youlpring.jws.controller.login.LoginController;
import com.youlpring.jws.controller.login.SessionReloadController;

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
        controllerMap.put("/home", HomeController.INSTANCE);
        controllerMap.put("/index", HomeController.INSTANCE);
        controllerMap.put("/login", LoginController.INSTANCE);
        controllerMap.put("/logout", LogoutController.INSTANCE);
        controllerMap.put("/user", UserController.INSTANCE);
        controllerMap.put("/register", UserRegisterController.INSTANCE);
        controllerMap.put("/session/reload", SessionReloadController.INSTANCE);
    }

    public static Controller getController(String url) {
        return controllerMap.getOrDefault(url, DefaultController.INSTANCE);
    }
}
