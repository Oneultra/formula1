package org.pavelkonrashov.formula1.provider;

import org.junit.jupiter.api.Test;
import org.pavelkonrashov.formula1.domain.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacerViewProviderImplTest {
    private final RacerViewProvider racerViewProvider = new RacerViewProviderImpl();

    @Test
    void provideRacerViewShouldReturnCorrectStringWhenGetAllArguments() {
        LocalTime startTimeABC = LocalTime.of(12, 0, 0, 0);
        LocalTime startTimeCDE = LocalTime.of(12, 10, 0, 0);
        LocalTime startTimeFGH = LocalTime.of(12, 15, 0, 0);
        LocalTime startTimeIJK = LocalTime.of(12, 20, 0, 0);
        LocalTime endTimeABC = LocalTime.of(12, 1, 11, 111000000);
        LocalTime endTimeCDE = LocalTime.of(12, 13, 22, 222000000);
        LocalTime endTimeFGH = LocalTime.of(12, 18, 33, 333000000);
        LocalTime endTimeIJK = LocalTime.of(12, 24, 44, 444000000);

        Racer firstRacer = Racer.builder().withAbbreviation("ABC").withName("Buzz")
                .withTeam("Team One").withBestTime(Duration.between(startTimeABC, endTimeABC)).build();

        Racer secondRacer = Racer.builder().withAbbreviation("CDE").withName("Edward")
                .withTeam("Team Two").withBestTime(Duration.between(startTimeCDE, endTimeCDE)).build();

        Racer thirdRacer = Racer.builder().withAbbreviation("FGH").withName("Barnie")
                .withTeam("Team Three").withBestTime(Duration.between(startTimeFGH, endTimeFGH)).build();

        Racer fourthRacer = Racer.builder().withAbbreviation("IJK").withName("Wallie")
                .withTeam("Team Four").withBestTime(Duration.between(startTimeIJK, endTimeIJK)).build();

        List<Racer> bestRacers = new ArrayList<>();
        bestRacers.add(firstRacer);
        bestRacers.add(secondRacer);
        bestRacers.add(thirdRacer);
        bestRacers.add(fourthRacer);

        StringBuilder formattedView = new StringBuilder();
        String expected = formattedView.append(
                String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 1, "Buzz", "Team One", 1, 11, 111))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 2, "Edward", "Team Two", 3, 22, 222))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 3, "Barnie", "Team Three", 3, 33, 333))
                .append("------------------------------------------------------------\n")
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 4, "Wallie", "Team Four", 4, 44, 444))
                .toString();

        String actual = racerViewProvider.provideRacerView(bestRacers, 3);

        assertThat(actual).isEqualTo(expected);
    }
}
