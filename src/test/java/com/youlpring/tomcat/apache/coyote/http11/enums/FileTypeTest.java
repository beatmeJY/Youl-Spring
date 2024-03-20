package com.youlpring.tomcat.apache.coyote.http11.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit] FileType 테스트")
class FileTypeTest {

    private final String htmlType = "test.html";
    private final String cssType = "test.css";
    private final String scssType = "test.scss";
    private final String jpgType = "test.jpg";

    private final String unKnownType = "test.xxx";

    @Test
    @DisplayName("파일 확장자를 올바르게 체크한다.")
    void valueOfFilenameSuccess() {
        assertEquals(FileType.HTML, FileType.valueOfFilename(htmlType));
        assertEquals(FileType.CSS, FileType.valueOfFilename(cssType));
        assertEquals(FileType.JPG, FileType.valueOfFilename(jpgType));
    }

    @Test
    @DisplayName("입력되지 않은 파일 확장자는 반환에 실패한다.")
    void valueOfFilenameFail() {
        assertNull(FileType.valueOfFilename(unKnownType));
    }

    @ParameterizedTest
    @ValueSource(strings = {cssType, scssType, jpgType})
    @DisplayName("css, js 확장자 등은 정적 파일이다.")
    void isStaticFile(String type) {
        assertTrue(FileType.isStaticFile(type));
    }

    @Test
    @DisplayName("HTML 확장자는 동적처리하므로 정적파일 처리하지 않는다.")
    void isNotStaticFile() {
        assertFalse(FileType.isStaticFile(htmlType));
    }

    @Test
    @DisplayName("확장자 별로 알맞는 ContentType을 반환한다.")
    void getContentType() {
        assertEquals(ContentType.TEXT_CSS, FileType.valueOfFilename(cssType).getContentType());
        assertEquals(ContentType.TEXT_CSS, FileType.valueOfFilename(scssType).getContentType());
        assertEquals(ContentType.IMAGE_JPEG, FileType.valueOfFilename(jpgType).getContentType());
        assertEquals(ContentType.TEXT_HTML, FileType.valueOfFilename(htmlType).getContentType());
    }
}
