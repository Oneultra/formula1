package org.pavelkonrashov.formula1.reader;

import java.util.List;

public interface FileReader {
    List<String> readFile(String filePath);
}
