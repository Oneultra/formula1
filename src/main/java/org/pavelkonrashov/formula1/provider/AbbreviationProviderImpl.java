package org.pavelkonrashov.formula1.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbbreviationProviderImpl implements AbbreviationProvider {

    public static final int BEGIN_INDEX = 0;
    public static final int END_INDEX = 3;
    public static final int PLACE_IN_LINE = 4;

    @Override
    public Map<String, String> provideAbbreviation(List<String> abbreviationData) {
        Map<String, String> nameAndTeamAbbreviation = new HashMap<>();

        abbreviationData.forEach(line -> nameAndTeamAbbreviation
                .put(line.substring(BEGIN_INDEX, END_INDEX), line.substring(PLACE_IN_LINE)));

        return nameAndTeamAbbreviation;
    }
}
