package com.youlpring.jws.controller.User;

import com.youlpring.common.db.InitDbBase;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.model.user.User;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@DisplayName("[Unit] UserController 테스트")
class UserControllerTest extends InitDbBase {

    private final UserController userController = UserController.INSTANCE;
    private final static String USER_INFO = "userInfo";

    @Test
    @DisplayName("GET 요청 시 현재 사용자 정보를 리턴한다.")
    void doGetSuccess() {
        InMemoryUserRepository.save(new User(ACCOUNT_VALUE, PASSWORD_VALUE, EMAIL_KEY));

        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        Mockito.when(mockRequest.getBodyValue(ACCOUNT_KEY)).thenReturn(ACCOUNT_VALUE);

        userController.doGet(mockRequest, response);
        User modelUser = (User) response.getModelAndView().getModelValue(USER_INFO);

        assertEquals(ACCOUNT_VALUE, modelUser.getAccount());
    }
}
