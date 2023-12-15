package com.youlpring.tomcat.apache.util;

import com.youlpring.tomcat.exception.HttpMessageException;
import com.youlpring.tomcat.exception.IORuntimeException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static String getFileReadString(String fileName) {
        if (fileName == null) {
            return null;
        }
        URL resourceURL = FileUtil.class.getClassLoader().getResource("static" + fileName);
        if (resourceURL == null) {
            return null;
        }
        try {
            return Files.readString(Path.of(resourceURL.toURI()));
        } catch (IOException e) {
            throw new IORuntimeException("I/O 작업 중 오류가 발생하였습니다.", e);
        } catch (URISyntaxException e) {
            throw new HttpMessageException("URI 정보가 올바르지 않습니다.");
        }
    }
}
