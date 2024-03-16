package com.youlpring;


import com.youlpring.tomcat.apache.catalina.connector.Connector;
import com.youlpring.tomcat.apache.catalina.startup.Tomcat;

public class YoulpringApplication {

    public static void main(String[] args) {
        Connector connector = new Connector();
        final Tomcat tomcat = new Tomcat();
        tomcat.start(connector);
    }
}
