package org.pavelkonrashov.formula1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pavelkonrashov.formula1.domain.RaceData;
import org.pavelkonrashov.formula1.domain.Racer;
import org.pavelkonrashov.formula1.provider.AbbreviationProvider;
import org.pavelkonrashov.formula1.provider.BestTimeProvider;
import org.pavelkonrashov.formula1.provider.RaceProvider;
import org.pavelkonrashov.formula1.provider.RacerViewProvider;
import org.pavelkonrashov.formula1.reader.FileReader;
import org.pavelkonrashov.formula1.validator.Validator;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class RaceStatisticsMakerTest {
    @Mock
    private AbbreviationProvider mockedAbbreviationProvider;

    @Mock
    private BestTimeProvider mockedBestTimeProvider;

    @Mock
    private RaceProvider mockedRaceProvider;

    @Mock
    private RacerViewProvider mockedRacerViewProvider;

    @Mock
    private FileReader mockedFileReader;

    @Mock
    private Validator mockedValidator;

    @InjectMocks
    private RaceStatisticsMaker raceStatisticsMaker;

    @Test
    void provideRaceStatisticShouldReturnStringWhenGetAllArguments() {
        String startLog = "start.log";
        String endLog = "end.log";
        String abbreviationTxt = "abbreviation.txt";
        int qualifiedRacers = 4;

        RaceData raceData = RaceData.builder().withStartLogFile(startLog).withEndLogFile(endLog)
                .withAbbreviationTxtFile(abbreviationTxt).withQualifiedRacers(qualifiedRacers).build();

        List<String> abbreviation = new ArrayList<>();
        abbreviation.add("ABC_Buzz_Team One");
        abbreviation.add("CDE_Edward_Team Two");
        abbreviation.add("FGH_Barnie_Team Three");
        abbreviation.add("IJK_Wallie_Team Four");

        List<String> startTime = new ArrayList<>();
        List<String> endTime = new ArrayList<>();

        startTime.add("ABC2018-05-24_12:02:58.000");
        startTime.add("CDE2018-05-24_12:12:58.000");
        startTime.add("FGH2018-05-24_12:22:58.000");
        startTime.add("IJK2018-05-24_12:32:58.000");

        endTime.add("ABC2018-05-24_12:03:58.000");
        endTime.add("CDE2018-05-24_12:13:58.000");
        endTime.add("FGH2018-05-24_12:23:58.000");
        endTime.add("IJK2018-05-24_12:33:58.000");

        LocalTime startTimeABC = LocalTime.of(12, 2, 58, 0);
        LocalTime startTimeCDE = LocalTime.of(12, 12, 58, 0);
        LocalTime startTimeFGH = LocalTime.of(12, 22, 58, 0);
        LocalTime startTimeIJK = LocalTime.of(12, 32, 58, 0);
        LocalTime endTimeABC = LocalTime.of(12, 3, 58, 0);
        LocalTime endTimeCDE = LocalTime.of(12, 13, 58, 0);
        LocalTime endTimeFGH = LocalTime.of(12, 23, 58, 0);
        LocalTime endTimeIJK = LocalTime.of(12, 33, 58, 0);

        Map<String, String> provideAbbreviation = new HashMap<>();
        provideAbbreviation.put("ABC", "Buzz_Team One");
        provideAbbreviation.put("CDE", "Edward_Team Two");
        provideAbbreviation.put("FGH", "Barnie_Team Three");
        provideAbbreviation.put("IJK", "Wallie_Team Four");

        Map<String, Duration> bestTime = new HashMap<>();
        bestTime.put("ABC", Duration.between(startTimeABC, endTimeABC));
        bestTime.put("CDE", Duration.between(startTimeCDE, endTimeCDE));
        bestTime.put("FGH", Duration.between(startTimeFGH, endTimeFGH));
        bestTime.put("IJK", Duration.between(startTimeIJK, endTimeIJK));

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

        when(mockedFileReader.readFile(anyString())).thenReturn(abbreviation)
                .thenReturn(startTime).thenReturn(endTime);
        when(mockedAbbreviationProvider.provideAbbreviation(anyList())).thenReturn(provideAbbreviation);
        when(mockedBestTimeProvider.bestTime(anyList(), anyList())).thenReturn(bestTime);
        when(mockedRaceProvider.provideRace(anyMap(), anyMap())).thenReturn(bestRacers);
        when(mockedRacerViewProvider.provideRacerView(anyList(), anyInt())).thenReturn(expected);

        String actual = raceStatisticsMaker.provideRaceStatistic(raceData);

        assertThat(actual).isEqualTo(expected);

        verify(mockedFileReader, times(3)).readFile(anyString());
        verify(mockedAbbreviationProvider).provideAbbreviation(any());
        verify(mockedBestTimeProvider).bestTime(any(), any());
        verify(mockedRaceProvider).provideRace(anyMap(), anyMap());
        verify(mockedRacerViewProvider).provideRacerView(anyList(), anyInt());
    }

    @Test
    void provideRaceStatisticShouldThrowIllegalArgumentExceptionWhenGetWrongFilePath() {
        String startLog = "start.log";
        String endLog = ".end.log";
        String abbreviationTxt = "abbreviation.txt";
        int qualifiedRacers = 4;

        RaceData raceData = RaceData.builder().withStartLogFile(startLog).withEndLogFile(endLog)
                .withAbbreviationTxtFile(abbreviationTxt).withQualifiedRacers(qualifiedRacers).build();

        doThrow(new IllegalArgumentException()).when(mockedValidator).validate(anyString());

        assertThrows(IllegalArgumentException.class, () -> raceStatisticsMaker.provideRaceStatistic(raceData));
        verifyNoMoreInteractions(mockedFileReader, mockedAbbreviationProvider, mockedBestTimeProvider,
                mockedRaceProvider, mockedRacerViewProvider);
    }
}
