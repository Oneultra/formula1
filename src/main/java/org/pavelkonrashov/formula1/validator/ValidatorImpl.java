package org.pavelkonrashov.formula1.validator;

import java.io.File;

public class ValidatorImpl implements Validator {
    @Override
    public void validate(String filePath) {
        File file = new File(filePath);

        if (filePath.isEmpty()) {
            throw new IllegalArgumentException("Path file is empty");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("File in this path not exist");
        }
    }

    @Override
    public void validate(int qualifiedRacers) {
        if (qualifiedRacers < 1) {
            throw new IllegalArgumentException("Qualified racers is less than one");
        }
    }
}
