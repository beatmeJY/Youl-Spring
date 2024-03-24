package com.youlpring.Fixture.tomcat.coyote.http11;

import com.youlpring.Fixture.common.TestConfigFixture;
import com.youlpring.tomcat.apache.coyote.http11.constants.HttpHeaderConstant;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpMethod;
import com.youlpring.tomcat.apache.coyote.http11.enums.HttpProtocol;

import java.util.HashMap;
import java.util.Map;

public final class RequestFixture {

    private RequestFixture() {
        throw new AssertionError("올바른 방식의 객체를 생성해주세요");
    }

    public static final String STATIC_FILE_REQUEST_URL = "/youlpring.txt";
    public static final String HOME_REQUEST_URL = "/index";

    public final static String ACCOUNT_KEY = "account";
    public final static String ACCOUNT_VALUE = "user";
    public final static String PASSWORD_KEY = "password";
    public final static String PASSWORD_VALUE = "1234";
    public final static String EMAIL_KEY = "email";
    public final static String EMAIL_VALUE = "test@email.com";

    public static final Map<String, Object> bodyMap = new HashMap<>();

    static {
        bodyMap.put(ACCOUNT_KEY, ACCOUNT_VALUE);
        bodyMap.put(PASSWORD_KEY, PASSWORD_VALUE);
        bodyMap.put(EMAIL_KEY, EMAIL_VALUE);
    }

    public static String requestAll() {
        return firstHeader(HOME_REQUEST_URL)
                + HttpHeaderConstant.CONTENT_LENGTH + ": " + body().length() + "\r\n"
                + header()
                + body();
    }

    public static String firstHeader(String url) {
        return HttpMethod.GET.name() + " " +
                url + " " +
                HttpProtocol.HTTP_1_1.getProtocol() + "\r\n";
    }

    public static String header() {
        return HttpHeaderConstant.HOST + ": " + TestConfigFixture.TEST_SERVER_IP_PORT + "\r\n\r\n";
    }

    public static String body() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (String key : bodyMap.keySet()) {
            if (isFirst) {
                addBodyData(builder, key, bodyMap.get(key));
                isFirst = false;
                continue;
            }
            builder.append("&");
            addBodyData(builder, key, bodyMap.get(key));
        }
        return builder.toString();
    }

    private static void addBodyData(StringBuilder builder, String key, Object value) {
        builder.append(key);
        builder.append("=");
        builder.append(value);
    }
}
