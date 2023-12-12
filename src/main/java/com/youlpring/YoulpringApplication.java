package com.youlpring;


import com.youlpring.tomcat.apache.catalina.startup.Tomcat;

public class YoulpringApplication {

    public static void main(String[] args) {
        final var tomcat = new Tomcat();
        tomcat.start();
    }
}
