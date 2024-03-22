package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;

@DisplayName("[Unit] DefaultController 테스트")
class DefaultControllerTest {

    private final static DefaultController defaultController = DefaultController.INSTANCE;

    @ParameterizedTest
    @ValueSource(strings = {"doGet", "doPost", "doPatch", "doPut", "doDelete"})
    @DisplayName("기본 컨트롤러가 호출되면 모두 홈페이지로 리다이렉션 한다.")
    void doGet(String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse mockResponse = mock(HttpResponse.class);
        Method doGet = defaultController.getClass().getDeclaredMethod(methodName, HttpRequest.class, HttpResponse.class);
        doGet.invoke(defaultController, mockRequest, mockResponse);

        Mockito.verify(mockResponse).serverRedirect("/");
    }
}
