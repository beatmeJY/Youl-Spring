package com.youlpring;


import com.youlpring.common.core.DefaultInitializer;
import com.youlpring.tomcat.apache.catalina.connector.Connector;
import com.youlpring.tomcat.apache.catalina.startup.Tomcat;

public class YoulpringApplication {

    public static void main(String[] args) {
        Connector connector = new Connector();
        final Tomcat tomcat = new Tomcat();
        DefaultInitializer defaultInitializer = new DefaultInitializer();
        tomcat.start(connector, defaultInitializer);
    }
}
