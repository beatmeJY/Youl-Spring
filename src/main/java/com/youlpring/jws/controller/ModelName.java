package com.youlpring.jws.controller;

public enum ModelName {
    USERINFO("userInfo");

    private final String name;

    ModelName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
