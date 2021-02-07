package org.pavelkonrashov.formula1.reader;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileReaderImplTest {
    private final FileReader fileReader = new FileReaderImpl();

    @Test
    void readFileShouldReturnListOfStringsWhenGetCorrectFilePath() {
        String filePath = "./src/test/resources/testFile.txt";
        List<String> expected = new ArrayList<>();

        expected.add("one");
        expected.add("two");
        expected.add("three");
        expected.add("four");
        expected.add("five");

        List<String> actual = fileReader.readFile(filePath);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void readFileShouldThrowIllegalArgumentExceptionWhenGetWrongFilePath() {
        String filePath = "Wrong file path";

        assertThatThrownBy(() -> fileReader.readFile(filePath)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("File at this path not exist: " + filePath);
    }
}
