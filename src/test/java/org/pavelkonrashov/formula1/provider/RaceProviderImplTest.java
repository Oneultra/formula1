package org.pavelkonrashov.formula1.provider;

import org.junit.jupiter.api.Test;
import org.pavelkonrashov.formula1.domain.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RaceProviderImplTest {
    private final RaceProvider raceProvider = new RaceProviderImpl();

    @Test
    void provideRaceShouldReturnListOfRacersSortedByPlace() {

        LocalTime startTimeABC = LocalTime.of(12, 2, 58, 0);
        LocalTime startTimeCDE = LocalTime.of(12, 12, 58, 0);
        LocalTime startTimeFGH = LocalTime.of(12, 22, 58, 0);
        LocalTime startTimeIJK = LocalTime.of(12, 32, 58, 0);
        LocalTime endTimeABC = LocalTime.of(12, 3, 58, 0);
        LocalTime endTimeCDE = LocalTime.of(12, 13, 58, 0);
        LocalTime endTimeFGH = LocalTime.of(12, 23, 58, 0);
        LocalTime endTimeIJK = LocalTime.of(12, 33, 58, 0);

        Racer firstRacer = Racer.builder().withAbbreviation("ABC").withName("Buzz")
                .withTeam("Team One").withBestTime(Duration.between(startTimeABC, endTimeABC)).build();

        Racer secondRacer = Racer.builder().withAbbreviation("CDE").withName("Edward")
                .withTeam("Team Two").withBestTime(Duration.between(startTimeCDE, endTimeCDE)).build();

        Racer thirdRacer = Racer.builder().withAbbreviation("FGH").withName("Barnie")
                .withTeam("Team Three").withBestTime(Duration.between(startTimeFGH, endTimeFGH)).build();

        Racer fourthRacer = Racer.builder().withAbbreviation("IJK").withName("Wallie")
                .withTeam("Team Four").withBestTime(Duration.between(startTimeIJK, endTimeIJK)).build();

        Map<String, String> provideAbbreviation = new HashMap<>();
        Map<String, Duration> bestTime = new HashMap<>();

        provideAbbreviation.put("ABC", "Buzz_Team One");
        provideAbbreviation.put("CDE", "Edward_Team Two");
        provideAbbreviation.put("FGH", "Barnie_Team Three");
        provideAbbreviation.put("IJK", "Wallie_Team Four");

        bestTime.put("ABC", Duration.between(startTimeABC, endTimeABC));
        bestTime.put("CDE", Duration.between(startTimeCDE, endTimeCDE));
        bestTime.put("FGH", Duration.between(startTimeFGH, endTimeFGH));
        bestTime.put("IJK", Duration.between(startTimeIJK, endTimeIJK));

        List<Racer> expected = new ArrayList<>();
        expected.add(firstRacer);
        expected.add(secondRacer);
        expected.add(thirdRacer);
        expected.add(fourthRacer);

        List<Racer> actual = raceProvider.provideRace(provideAbbreviation, bestTime);

        assertThat(actual).isEqualTo(expected);
    }
}
