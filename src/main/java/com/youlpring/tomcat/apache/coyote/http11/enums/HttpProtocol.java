package com.youlpring.tomcat.apache.coyote.http11.enums;

public enum HttpProtocol {
    HTTP_1_1("HTTP/1.1"),
    HTTP_2("HTTP/2"),
    HTTP_3("HTTP/3");

    private final String protocol;

    HttpProtocol(String protocol) {
        this.protocol = protocol;
    }

    public static HttpProtocol valueOfString(String value) {
        for (HttpProtocol httpProtocol : HttpProtocol.values()) {
            if (httpProtocol.protocol.equals(value)) {
                return httpProtocol;
            }
        }
        return null;
    }

    public String getProtocol() {
        return protocol;
    }
}
