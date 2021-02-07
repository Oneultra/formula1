package org.pavelkonrashov.formula1.provider;

import java.util.List;
import java.util.Map;

public interface AbbreviationProvider {
    Map<String, String> provideAbbreviation(List<String> abbreviationData);
}
