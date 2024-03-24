package com.youlpring.jws.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.HOME_REQUEST_URL;
import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.bodyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[Unit] ModelAndView 테스트")
class ModelAndViewTest {

    @Test
    @DisplayName("뷰를 저장하는데 성공한다.")
    void getViewName() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOME_REQUEST_URL);

        assertEquals(HOME_REQUEST_URL, modelAndView.getViewName());
    }

    @Test
    @DisplayName("모델을 저장하는 성공한다.")
    void getModelValue() {
        ModelAndView modelAndView = new ModelAndView();
        bodyMap.keySet().forEach(key -> modelAndView.addModel(key, bodyMap.get(key)));

        assertEquals(bodyMap.size(), modelAndView.getModelKeys().size());
        for (String modelKey : modelAndView.getModelKeys()) {
            assertEquals(bodyMap.get(modelKey), modelAndView.getModelValue(modelKey));
        }
    }
}
