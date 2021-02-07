package org.pavelkonrashov.formula1.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> readFile(String filePath) {
        try(Stream<String> timesData = Files.lines(Paths.get(filePath))) {
            return timesData.collect(Collectors.toList());
        } catch (IOException exception) {
            throw new IllegalArgumentException("File at this path not exist: " + filePath);
        }
    }
}
