package com.youlpring.jws.db;

import com.youlpring.common.db.InitDbBase;
import com.youlpring.jws.model.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.youlpring.Fixture.user.UserFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("[Unit] InMemoryUserRepository 테스트")
class InMemoryUserRepositoryTest extends InitDbBase {

    @Test
    @DisplayName("DB에 회원 저장에 성공한다.")
    void save() {
        InMemoryUserRepository.save(USER);

        User findUser = InMemoryUserRepository.findByAccount(ACCOUNT);

        assertEquals(ACCOUNT, findUser.getAccount());
        assertEquals(PASSWORD, findUser.getPassword());
        assertEquals(EMAIL, findUser.getEmail());
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

        User findUser = InMemoryUserRepository.findByAccount(ACCOUNT);
        assertEquals(ACCOUNT, findUser.getAccount());

        InMemoryUserRepository.deleteUser(ACCOUNT);

        assertNull(InMemoryUserRepository.findByAccount(ACCOUNT));
    }
}
