package com.youlpring.tomcat.apache.coyote.http11.enums;

public enum FileType {
    HTML(".html", ContentType.TEXT_HTML),
    CSS(".css", ContentType.TEXT_CSS),
    SCSS(".scss", ContentType.TEXT_CSS),
    js(".js", ContentType.APPLICATION_JAVASCRIPT);

    private final String extension;
    private final ContentType contentType;

    FileType(String extension, ContentType contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public static FileType valueOfFilename(String filename) {
        if (filename == null || filename.indexOf(".") == -1) {
            return null;
        }
        String fileExtension = filename.substring(filename.indexOf("."));
        for (FileType value : FileType.values()) {
            if (value.extension.equals(fileExtension)) {
                return value;
            }
        }
        return null;
    }

    public ContentType getContentType() {
        return this.contentType;
    }
}
