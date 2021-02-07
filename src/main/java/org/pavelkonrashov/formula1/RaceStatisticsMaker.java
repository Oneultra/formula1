package org.pavelkonrashov.formula1;

import org.pavelkonrashov.formula1.domain.RaceData;
import org.pavelkonrashov.formula1.domain.Racer;
import org.pavelkonrashov.formula1.provider.AbbreviationProvider;
import org.pavelkonrashov.formula1.provider.BestTimeProvider;
import org.pavelkonrashov.formula1.provider.RaceProvider;
import org.pavelkonrashov.formula1.provider.RacerViewProvider;
import org.pavelkonrashov.formula1.validator.Validator;
import org.pavelkonrashov.formula1.reader.FileReader;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RaceStatisticsMaker {
    private final AbbreviationProvider abbreviationProvider;
    private final BestTimeProvider bestTimeProvider;
    private final RaceProvider raceProvider;
    private final RacerViewProvider racerViewProvider;
    private final Validator validator;
    private final FileReader fileReader;

    public RaceStatisticsMaker(AbbreviationProvider abbreviationProvider, BestTimeProvider bestTimeProvider,
                               RaceProvider raceProvider, RacerViewProvider racerViewProvider,
                               Validator validator, FileReader fileReader) {
        this.abbreviationProvider = abbreviationProvider;
        this.bestTimeProvider = bestTimeProvider;
        this.raceProvider = raceProvider;
        this.racerViewProvider = racerViewProvider;
        this.validator = validator;
        this.fileReader = fileReader;
    }

    public String provideRaceStatistic(RaceData raceData) {
        String startLog = raceData.getStartLogFile();
        String endLog = raceData.getEndLogFile();
        String abbreviationsTxt = raceData.getAbbreviationsTxtFile();
        int qualifiedRacers = raceData.getQualifiedRacers();

        validator.validate(startLog);
        validator.validate(endLog);
        validator.validate(abbreviationsTxt);
        validator.validate(qualifiedRacers);

        List<String> startFileLog = fileReader.readFile(startLog);
        List<String> endFileLog = fileReader.readFile(endLog);
        List<String> abbreviationsFileTxt = fileReader.readFile(abbreviationsTxt);

        Map<String, String> nameAndTeamAbbreviation = abbreviationProvider.provideAbbreviation(abbreviationsFileTxt);
        Map<String, Duration> bestTime = bestTimeProvider.bestTime(startFileLog, endFileLog);

        List<Racer> bestRacers = raceProvider.provideRace(nameAndTeamAbbreviation, bestTime);

        return racerViewProvider.provideRacerView(bestRacers, qualifiedRacers);
    }
}
