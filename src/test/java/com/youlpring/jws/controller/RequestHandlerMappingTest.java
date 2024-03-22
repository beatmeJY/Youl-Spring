package com.youlpring.jws.controller;

import com.youlpring.jws.controller.User.UserController;
import com.youlpring.jws.controller.User.UserRegisterController;
import com.youlpring.jws.controller.login.LoginController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] RequestHandlerMapping 테스트")
class RequestHandlerMappingTest {

    @ParameterizedTest
    @MethodSource("urlMapping")
    @DisplayName("컨트롤러 매핑에 성공한다.")
    void getControllerSuccess(String url, Controller controller) {
        assertEquals(controller, RequestHandlerMapping.getController(url));
    }

    private static Stream<Arguments> urlMapping() {
        return Stream.of(
            Arguments.of("/", HomeController.INSTANCE),
            Arguments.of("/home", HomeController.INSTANCE),
            Arguments.of("/index", HomeController.INSTANCE),
            Arguments.of("/login", LoginController.INSTANCE),
            Arguments.of("/user", UserController.INSTANCE),
            Arguments.of("/register", UserRegisterController.INSTANCE),
            Arguments.of("/test", DefaultController.INSTANCE)
        );
    }
}
