package org.pavelkonrashov.formula1.validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidatorImplTest {
    private final Validator validator = new ValidatorImpl();

    @Test
    void validatorShouldThrowIllegalArgumentExceptionWhenPathFileIsEmpty() {
        String path = "";

        assertThatThrownBy(() -> validator.validate(path)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path file is empty");
    }

    @Test
    void validatorShouldThrowIllegalArgumentExceptionWhenPathFileIsNoExist() {
        String path = "jfdshgjs";

        assertThatThrownBy(() -> validator.validate(path)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("File in this path not exist");
    }

    @Test
    void validatorShouldThrownIllegalArgumentExceptionWhenQualifiedRacersIsLessThanOne() {
        int qualifiedRacers = 0;

        assertThatThrownBy(() -> validator.validate(qualifiedRacers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Qualified racers is less than one");
    }

    @Test
    void validatorShouldNotThrowIllegalArgumentExceptionWhenPathFileIsCorrect() {
        String path = "./src/main/resources/start.log";
        
        assertDoesNotThrow(() -> validator.validate(path));
    }

    @Test
    void validatorShouldNotThrowIllegalArgumentExceptionWhenQualifiedRacersIsMoreThanOne() {
        int qualifiedRacers = 15;

        assertDoesNotThrow(() -> validator.validate(qualifiedRacers));
    }
}
