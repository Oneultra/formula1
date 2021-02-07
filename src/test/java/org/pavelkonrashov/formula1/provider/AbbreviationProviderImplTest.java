package org.pavelkonrashov.formula1.provider;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AbbreviationProviderImplTest {
    private final AbbreviationProvider abbreviationProvider = new AbbreviationProviderImpl();

    @Test
    void provideAbbreviationShouldReturnMapWhenGetListOfStringsWithData() {
        List<String> dataList = new ArrayList<>();
        dataList.add("ABC_Buzz_Team One");
        dataList.add("CDE_Edward_Team Two");

        Map<String, String> expected = new HashMap<>();
        expected.put("ABC", "Buzz_Team One");
        expected.put("CDE", "Edward_Team Two");

        Map<String, String> actual = abbreviationProvider.provideAbbreviation(dataList);

        assertThat(actual).isEqualTo(expected);
    }
}
