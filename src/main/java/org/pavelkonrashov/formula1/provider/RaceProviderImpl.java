package org.pavelkonrashov.formula1.provider;

import org.pavelkonrashov.formula1.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceProviderImpl implements RaceProvider {

    public static final String DELIMITER = "_";
    public static final int NAME_PLACE = 0;
    public static final int TEAM_PLACE = 1;

    @Override
    public List<Racer> provideRace(Map<String, String> abbreviation, Map<String, Duration> bestTime) {
        return bestTime.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).map(o -> Racer.builder()
                .withName(getFromAbbreviation(NAME_PLACE, abbreviation.get(o.getKey())))
                .withTeam(getFromAbbreviation(TEAM_PLACE, abbreviation.get(o.getKey())))
                .withAbbreviation(o.getKey())
                .withBestTime(o.getValue())
                .build()).collect(Collectors.toList());
    }

    private String getFromAbbreviation(int place, String abbreviationLine) {
        return abbreviationLine.split(DELIMITER)[place];
    }
}
