package com.youlpring.jws.controller.User;

import com.youlpring.common.db.InitDbBase;
import com.youlpring.jws.controller.ModelName;
import com.youlpring.jws.db.InMemoryUserRepository;
import com.youlpring.jws.model.user.User;
import com.youlpring.jws.model.user.UserDTO;
import com.youlpring.tomcat.apache.coyote.http11.context.Session;
import com.youlpring.tomcat.apache.coyote.http11.context.UserSessionInfo;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.youlpring.Fixture.jws.user.UserFixture.ACCOUNT;
import static com.youlpring.Fixture.jws.user.UserFixture.PASSWORD;
import static com.youlpring.Fixture.tomcat.coyote.http11.RequestFixture.EMAIL_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("[Unit] UserController 테스트")
class UserControllerTest extends InitDbBase {

    private final UserController userController = UserController.INSTANCE;

    @Test
    @DisplayName("GET 요청 시 현재 사용자 정보를 리턴한다.")
    void doGetSuccess() {
        User user = new User(ACCOUNT, PASSWORD, EMAIL_KEY);
        InMemoryUserRepository.save(user);

        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse response = new HttpResponse();
        Session session = new Session(
                "sessionKey",
                new UserSessionInfo(user.getId(), user.getAccount(), user.getEmail()),
                LocalDateTime.now().plusMinutes(10));

        when(mockRequest.getSession()).thenReturn(session);
        userController.doGet(mockRequest, response);
        UserDTO modelUser = (UserDTO) response.getModelAndView().getModelValue(ModelName.USERINFO.getName());

        assertEquals(ACCOUNT, modelUser.getAccount());
    }
}
