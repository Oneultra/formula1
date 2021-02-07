package org.pavelkonrashov.formula1.provider;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestTimeProviderImpl implements BestTimeProvider {

    public static final String DELIMITER = "_";
    public static final int BEGIN_INDEX = 0;
    public static final int END_INDEX = 3;
    public static final int TIME_PLACE = 1;

    @Override
    public Map<String, Duration> bestTime(List<String> startTime, List<String> endTime) {
        Map<String, LocalTime> startTimeConverter = readTimeData(startTime);
        Map<String, LocalTime> endTimeConverter = readTimeData(endTime);

        return startTimeConverter.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        time -> Duration.between(time.getValue(), endTimeConverter.get(time.getKey()))));
    }

    private Map<String, LocalTime> readTimeData(List<String> timeData) {
        return timeData.stream().collect(Collectors
                .toMap(time -> time.substring(BEGIN_INDEX, END_INDEX),
                        time -> LocalTime.parse(time.split(DELIMITER)[TIME_PLACE], DateTimeFormatter.ISO_TIME)));
    }
}
