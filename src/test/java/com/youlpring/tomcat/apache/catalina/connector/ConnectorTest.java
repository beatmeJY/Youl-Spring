package com.youlpring.tomcat.apache.catalina.connector;

import com.youlpring.Fixture.common.TestConfigFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@DisplayName("[Unit] Connector 테스트")
class ConnectorTest {

    private static final int ACCEPT_COUNT = 10;

    @Test
    @DisplayName("지정된 Port 로 실행에 성공한다.")
    void ConnectorStart() throws IOException {
        Connector connector = new Connector(TestConfigFixture.TEST_SERVER_PORT, ACCEPT_COUNT);
        connector.start();

        URL url = new URL(TestConfigFixture.TEST_SERVER_IP_PORT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        Assertions.assertEquals(200, connection.getResponseCode());
    }
}
