package org.pavelkonrashov.formula1;

import org.pavelkonrashov.formula1.domain.RaceData;
import org.pavelkonrashov.formula1.provider.AbbreviationProvider;
import org.pavelkonrashov.formula1.provider.AbbreviationProviderImpl;
import org.pavelkonrashov.formula1.provider.BestTimeProvider;
import org.pavelkonrashov.formula1.provider.BestTimeProviderImpl;
import org.pavelkonrashov.formula1.provider.RacerViewProvider;
import org.pavelkonrashov.formula1.provider.RacerViewProviderImpl;
import org.pavelkonrashov.formula1.provider.RaceProvider;
import org.pavelkonrashov.formula1.provider.RaceProviderImpl;
import org.pavelkonrashov.formula1.reader.FileReader;
import org.pavelkonrashov.formula1.reader.FileReaderImpl;
import org.pavelkonrashov.formula1.validator.Validator;
import org.pavelkonrashov.formula1.validator.ValidatorImpl;

public class RaceConsoleApplication {
    public static void main(String[] args) {
        int qualifiedRacers = 15;
        String startLog = "./src/main/resources/start.log";
        String endLog = "./src/main/resources/end.log";
        String abbreviationsTxt = "./src/main/resources/abbreviations.txt";

        RaceData raceData = RaceData.builder().withStartLogFile(startLog)
                .withEndLogFile(endLog).withAbbreviationTxtFile(abbreviationsTxt)
                .withQualifiedRacers(qualifiedRacers).build();

        AbbreviationProvider abbreviationProvider = new AbbreviationProviderImpl();
        BestTimeProvider bestTimeProvider = new BestTimeProviderImpl();
        RaceProvider raceProvider = new RaceProviderImpl();
        RacerViewProvider racerViewProvider = new RacerViewProviderImpl();
        FileReader fileReader = new FileReaderImpl();
        Validator validator = new ValidatorImpl();

        RaceStatisticsMaker raceStatisticsMaker =
                new RaceStatisticsMaker(abbreviationProvider, bestTimeProvider, raceProvider,
                        racerViewProvider, validator, fileReader);

        System.out.println(raceStatisticsMaker.provideRaceStatistic(raceData));
    }
}
