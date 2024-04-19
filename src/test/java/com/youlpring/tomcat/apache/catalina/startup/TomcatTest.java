package com.youlpring.tomcat.apache.catalina.startup;

import com.youlpring.common.core.DefaultInitializer;
import com.youlpring.tomcat.apache.catalina.connector.Connector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("[Unit] Tomcat 테스트")
class TomcatTest {

    @Test
    @DisplayName("톰캣 작동을 성공한다.")
    void TomcatSuccess() {
        Connector connector = mock(Connector.class);
        Tomcat tomcat = new Tomcat();
        DefaultInitializer defaultInitializer = new DefaultInitializer();
        tomcat.start(connector, defaultInitializer);

        verify(connector, Mockito.times(1)).start();
        verify(connector, Mockito.times(1)).stop();
    }
}
