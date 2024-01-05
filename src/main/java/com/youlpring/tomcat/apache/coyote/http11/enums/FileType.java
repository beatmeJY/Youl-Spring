package com.youlpring.tomcat.apache.coyote.http11.enums;

public enum FileType {
    HTML(".html", ContentType.TEXT_HTML),
    CSS(".css", ContentType.TEXT_CSS),
    SCSS(".scss", ContentType.TEXT_CSS),
    JS(".js", ContentType.APPLICATION_JAVASCRIPT),
    JPG(".jpg", ContentType.IMAGE_JPEG),
    PNG(".png", ContentType.IMAGE_PNG),
    GIF(".gif", ContentType.IMAGE_GIF),
    ICO(".ico", ContentType.IMAGE_X_ICON);

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

    public static boolean isStaticFile(String filename) {
        FileType fileType = valueOfFilename(filename);
        return fileType != null && !FileType.HTML.equals(fileType);
    }

    public ContentType getContentType() {
        return this.contentType;
    }
}
