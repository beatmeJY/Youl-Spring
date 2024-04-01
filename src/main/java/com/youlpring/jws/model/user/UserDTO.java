package com.youlpring.jws.model.user;

public class UserDTO {

    private Long id;
    private final String account;
    private final String email;

    public UserDTO(User user) {
        id = user.getId();
        account = user.getAccount();
        email = user.getEmail();
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
