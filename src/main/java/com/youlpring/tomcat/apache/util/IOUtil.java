package com.youlpring.tomcat.apache.util;

import com.youlpring.tomcat.exception.IORuntimeException;

import java.io.BufferedReader;
import java.io.IOException;

public class IOUtil {

    public static String readLine(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IORuntimeException("I/O 작업 중 오류가 발생하였습니다.", e);
        }
    }

    public static boolean ready(BufferedReader bufferedReader) {
        try {
            return bufferedReader.ready();
        } catch (IOException e) {
            throw new IORuntimeException("I/O 작업 중 오류가 발생하였습니다.", e);
        }
    }
}
