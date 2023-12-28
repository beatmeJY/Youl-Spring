package com.youlpring.jws.db;


import com.youlpring.jws.exception.UserRegisterException;
import com.youlpring.jws.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryUserRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private static final Map<String, User> database = new ConcurrentHashMap<>();
    private static AtomicLong generateId = new AtomicLong();

    static {
        final User user1 = new User(null, "winter", "1234", "winter@gmail.com");
        final User user2 = new User(null, "summer", "1234", "summer@gmail.com");
        save(user1);
        save(user2);
    }

    private InMemoryUserRepository() {}

    public static void save(User user) {
        database.compute(user.getAccount(), (key, existUser) -> {
            if (existUser != null) {
                throw new UserRegisterException("이미 존재하는 계정입니다.");
            }
            if (user.getId() == null) {
                user.setId(generateUserId());
            }
            return user;
        });
        for (String s : database.keySet()) {
            log.info("{}", database.get(s));
        }
    }

    private static Long generateUserId() {
        return generateId.incrementAndGet();
    }

    public static User findByAccount(String account) {
        return Optional.ofNullable(database.get(account)).orElse(null);
    }
}
