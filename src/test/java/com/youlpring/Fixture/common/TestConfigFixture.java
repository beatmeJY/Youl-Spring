package com.youlpring.Fixture.common;

public final class TestConfigFixture {

    private TestConfigFixture() {
        throw new AssertionError("올바른 방식의 객체를 생성해주세요");
    }

    public static final String TEST_SERVER_Protocol = "http://";
    public static final String TEST_SERVER_IP = "127.0.0.1";
    public static final int TEST_SERVER_PORT = 8080;
    public static final String TEST_SERVER_IP_PORT = TEST_SERVER_Protocol + TEST_SERVER_IP + ":" + TEST_SERVER_PORT;
}
