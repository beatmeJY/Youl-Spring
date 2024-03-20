package com.youlpring.tomcat.apache.util;

import com.youlpring.Fixture.utill.FileFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("[Unit] FileUtil 테스트")
class FileUtilTest {

    @Test
    @DisplayName("파일 읽어오기에 성공한다.")
    void getStaticFileSuccess() {
        String text = FileUtil.getStaticFile(FileFixture.STATIC_FILE);

        assertEquals(FileFixture.readStaticTextFile(), text);
    }

    @Test
    @DisplayName("null을 넘기면 읽어오기에 실패한다.")
    void getStaticFileNullFail() {
        String staticFile = FileUtil.getStaticFile(null);

        assertNull(staticFile);
    }

    @Test
    @DisplayName("없는 파일은 읽어오기에 실패한다.")
    void getStaticFileEmptyFail() {
        String staticFile = FileUtil.getStaticFile("/empty.txt");

        assertNull(staticFile);
    }
}
