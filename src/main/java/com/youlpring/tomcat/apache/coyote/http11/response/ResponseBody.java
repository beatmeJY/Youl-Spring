package com.youlpring.tomcat.apache.coyote.http11.response;

import com.youlpring.tomcat.apache.coyote.http11.enums.FileType;
import com.youlpring.tomcat.apache.util.FileUtil;

public class ResponseBody {

    private String body;
    private FileType fileType;

    public ResponseBody(String url) {
        if (url == null || url == "") {
            return;
        }
        fileType = FileType.valueOfFilename(url);
        String file = FileUtil.getFileReadString(url);
        this.body = file;
    }

    public String getBody() {
        return body;
    }

    public String getContentType() {
        if (fileType == null) {
            return null;
        }
        return fileType.getContentType().getContentString();
    }

    public int getBodyLength() {
        if (body == null) {
            return 0;
        }
        return body.getBytes().length;
    }
}
