package com.youlpring.jws.model;

import com.youlpring.jws.exception.UserRegisterException;

public class User {

    private Long id;
    private final String account;
    private final String password;
    private final String email;

    public User(Long id, String account, String password, String email) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
    }

    public User(String account, String password, String email) {
        this(null, account, password, email);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setId(Long newId) {
        if (id != null) {
            throw new UserRegisterException("이미 등록된 계정입니다.");
        }
        this.id = newId;
    }

    public String getAccount() {
        return account;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
