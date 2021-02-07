package org.pavelkonrashov.formula1.validator;

public interface Validator {
    void validate(String filePath);

    void validate(int qualifiedRacers);
}
