package com.youlpring.Fixture.user;

import com.youlpring.jws.model.User;

public final class UserFixture {

    private UserFixture() {
        throw new AssertionError("올바른 방식의 객체를 생성해주세요");
    }

    public static final String ACCOUNT = "test";
    public static final String PASSWORD = "1234";
    public static final String EMAIL = "test@gmail.com";
    public static final User USER = new User(ACCOUNT, PASSWORD, EMAIL);
}
