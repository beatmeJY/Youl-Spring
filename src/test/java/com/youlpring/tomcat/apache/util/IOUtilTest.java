package com.youlpring.tomcat.apache.util;

import com.youlpring.Fixture.utill.FileFixture;
import com.youlpring.tomcat.exception.IORuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("[Unit] IOUtil 테스트")
class IOUtilTest {

    private static final byte[] textBytesFixture = FileFixture.readStaticTextFile().getBytes();

    @Test
    @DisplayName("readLine()을 성공한다.")
    void readLineSuccess() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(textBytesFixture)));

        for (String textLine : FileFixture.readStaticTextFile().split("\r\n")) {
            String readLine = IOUtil.readLine(bufferedReader);
            assertEquals(readLine, textLine);
        }
    }

    @Test
    @DisplayName("주어진 길이만큼 read()로 읽는데 성공한다.")
    void readByLengthSuccess() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(textBytesFixture)));

        char[] chars = IOUtil.readByLength(bufferedReader, FileFixture.readStaticTextFile().getBytes().length);

        assertEquals(String.valueOf(chars), FileFixture.readStaticTextFile());
    }

    @Test
    @DisplayName("주어진 길이만큼 read()로 읽는데 성공한다.")
    void readySuccess() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(textBytesFixture)));

        assertTrue(IOUtil.ready(bufferedReader));
    }

    @Test
    @DisplayName("IO 작업중 IOException 발생하면 사용자 예외로 전달한다.")
    void readLineFail() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenThrow(new IOException("Simulated IOException"));

        assertThrows(IORuntimeException.class, () -> IOUtil.readLine(mockReader));
    }
}
