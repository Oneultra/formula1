package org.pavelkonrashov.formula1.provider;

import org.pavelkonrashov.formula1.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface RaceProvider {
    List<Racer> provideRace(Map<String, String> abbreviation, Map<String, Duration> bestTime);
}
