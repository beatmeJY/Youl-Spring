package com.youlpring.jws.db;

import com.youlpring.jws.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.youlpring.Fixture.user.UserFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("[Unit] InMemoryUserRepository 테스트")
class InMemoryUserRepositoryTest {

    @Test
    @DisplayName("DB에 회원 저장에 성공한다.")
    void save() {
        InMemoryUserRepository.save(USER);
        try {
            User findUser = InMemoryUserRepository.findByAccount(ACCOUNT);

            assertEquals(ACCOUNT, findUser.getAccount());
            assertEquals(PASSWORD, findUser.getPassword());
            assertEquals(EMAIL, findUser.getEmail());
        } finally {
            InMemoryUserRepository.deleteUser(ACCOUNT);
        }
    }

    @Test
    @DisplayName("존재하지 않는 회원은 NULL 을 반환한다.")
    void findByAccountFail() {
        assertNull(InMemoryUserRepository.findByAccount(ACCOUNT));
    }

    @Test
    @DisplayName("회원을 삭제하는데 성공한다.")
    void deleteUserSuccess() {
        InMemoryUserRepository.save(USER);
        try {
            User findUser = InMemoryUserRepository.findByAccount(ACCOUNT);
            assertEquals(ACCOUNT, findUser.getAccount());

            InMemoryUserRepository.deleteUser(ACCOUNT);

            assertNull(InMemoryUserRepository.findByAccount(ACCOUNT));
        } finally {
            InMemoryUserRepository.deleteUser(ACCOUNT);
        }
    }
}
