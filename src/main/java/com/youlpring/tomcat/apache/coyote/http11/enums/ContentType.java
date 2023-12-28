package com.youlpring.tomcat.apache.coyote.http11.enums;

public enum ContentType {
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    TEXT_PLAIN("text/plain"),
    APPLICATION_JAVASCRIPT("application/javascript"),
    APPLICATION_JSON("application/json"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_X_ICON("image/x-icon");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getContentString() {
        return this.type + "; charset=utf-8";
    }
}
