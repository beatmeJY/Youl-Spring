package com.youlpring.jws.model.user;

public class UserDTO {

    private Long id;
    private final String account;
    private final String email;

    public UserDTO(Long id, String account, String email) {
        this.id = id;
        this.account = account;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }
}
