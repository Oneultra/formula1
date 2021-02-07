package org.pavelkonrashov.formula1.provider;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BestTimeProviderImplTest {
    private final BestTimeProvider bestTimeProvider = new BestTimeProviderImpl();

    @Test
    void bestTimeShouldReturnMapAbbreviation() {
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

        Map<String, Duration> expected = new HashMap<>();
        expected.put("ABC", Duration.between(startTimeABC, endTimeABC));
        expected.put("CDE", Duration.between(startTimeCDE, endTimeCDE));
        expected.put("FGH", Duration.between(startTimeFGH, endTimeFGH));
        expected.put("IJK", Duration.between(startTimeIJK, endTimeIJK));

        Map<String, Duration> actual = bestTimeProvider.bestTime(startTime, endTime);

        assertThat(expected).isEqualTo(actual);
    }
}
