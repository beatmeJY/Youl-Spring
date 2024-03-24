package com.youlpring.common.db;

import com.youlpring.jws.db.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;

public class InitDbBase {

    @BeforeEach
    void initDB() {
        InMemoryUserRepository.clear();
    }
}
