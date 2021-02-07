package org.pavelkonrashov.formula1.provider;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface BestTimeProvider {
    Map<String, Duration> bestTime(List<String> startTime, List<String> endTime);
}
