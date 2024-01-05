package com.youlpring.tomcat.apache.coyote.http11;

import com.youlpring.jws.controller.FrontController;
import com.youlpring.tomcat.apache.coyote.Processor;
import com.youlpring.tomcat.apache.coyote.http11.enums.FileType;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import com.youlpring.tomcat.apache.coyote.http11.response.ResponseBody;
import com.youlpring.tomcat.apache.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class Http11Processor implements Runnable, Processor {

    private static final Logger log = LoggerFactory.getLogger(Http11Processor.class);

    private final Socket connection;

    public Http11Processor(final Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        log.info("connect host: {}, port: {}", connection.getInetAddress(), connection.getPort());
        process(connection);
    }

    @Override
    public void process(final Socket connection) {
        try (final InputStream inputStream = connection.getInputStream();
             final OutputStream outputStream = connection.getOutputStream()) {

            HttpRequest request = new HttpRequest(new BufferedReader(new InputStreamReader(inputStream)));
            HttpResponse response = new HttpResponse();

            if (FileType.isStaticFile(request.getUrl())) {
                staticFileProcess(request, response);
            } else {
                FrontController.process(request, response);
            }
            outputStream.write(response.getHttpByte());
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void staticFileProcess(HttpRequest request, HttpResponse response) {
        response.setResponseBody(
            new ResponseBody(
                FileUtil.getStaticFile(request.getUrl()),
                FileType.valueOfFilename(request.getUrl()).getContentType()
            )
        );
    }
}
