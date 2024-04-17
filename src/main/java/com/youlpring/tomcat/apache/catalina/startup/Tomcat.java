package com.youlpring.tomcat.apache.catalina.startup;

import com.youlpring.common.core.Initializer;
import com.youlpring.tomcat.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Tomcat {

    private static final Logger log = LoggerFactory.getLogger(Tomcat.class);

    public void start(Connector connector, Initializer initializer) {
        connector.start();
        initializer.init();
        try {
            System.in.read();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            log.info("web server stop.");
            connector.stop();
        }
    }
}
