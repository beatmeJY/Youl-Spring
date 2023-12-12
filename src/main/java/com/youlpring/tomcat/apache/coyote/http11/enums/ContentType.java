package com.youlpring.tomcat.apache.coyote.http11.enums;

public enum ContentType {
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    APPLICATION_JAVASCRIPT("application/javascript");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getContentString() {
        return this.type + "; charset=utf-8";
    }
}
