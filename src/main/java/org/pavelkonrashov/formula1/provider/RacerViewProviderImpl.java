package org.pavelkonrashov.formula1.provider;

import org.pavelkonrashov.formula1.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RacerViewProviderImpl implements RacerViewProvider {

    public static final int FIRST_PLACE = 1;
    public static final String NEW_LINE = "\n";
    public static final String HYPHEN = "-";
    public static final String TIME_FORMAT = "%02d:%02d.%03d";
    public static final String PLACE_FORMAT = "%02d. ";
    public static final String NAME_FORMAT = "%-18s|";
    public static final String TEAM_FORMAT = "%-27s|";
    public static final int MILLIS_IN_ONE_MINUTE = 60000;
    public static final int MILLIS_IN_ONE_SECOND = 1000;

    @Override
    public String provideRacerView(List<Racer> racers, int qualifiedRacers) {
        StringBuilder formattedResult = new StringBuilder();
        AtomicInteger qualifiedPlaces = new AtomicInteger(FIRST_PLACE);

        racers.forEach(racer -> {
            if (qualifiedPlaces.get() == qualifiedRacers + 1) {
                int lineLength = formattedResult.toString().split(NEW_LINE)[1].length();
                formattedResult.append(createLine(lineLength));
            }
            formattedResult.append(String.format(PLACE_FORMAT, qualifiedPlaces.getAndIncrement()))
                    .append(String.format(NAME_FORMAT, racer.getName()))
                    .append(String.format(TEAM_FORMAT, racer.getTeam()))
                    .append(convertTime(racer.getBestTime()))
                    .append(NEW_LINE);
        });

        return formattedResult.toString();
    }

    private String createLine(int value) {
        StringBuilder insert = new StringBuilder();

        for (int i = 0; i < value; i++) {
            insert.append(HYPHEN);
        }
        insert.append(NEW_LINE);

        return insert.toString();
    }

    private String convertTime(Duration bestTime) {
        long bestTimeInMillis = bestTime.toMillis();
        long minutes = bestTimeInMillis / MILLIS_IN_ONE_MINUTE;
        long seconds = (bestTimeInMillis % MILLIS_IN_ONE_MINUTE) / MILLIS_IN_ONE_SECOND;
        long millis = (bestTimeInMillis % MILLIS_IN_ONE_MINUTE) % MILLIS_IN_ONE_SECOND;

        return String.format(TIME_FORMAT, minutes, seconds, millis);
    }
}
