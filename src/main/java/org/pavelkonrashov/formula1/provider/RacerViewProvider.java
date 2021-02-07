package org.pavelkonrashov.formula1.provider;

import org.pavelkonrashov.formula1.domain.Racer;

import java.util.List;

public interface RacerViewProvider {
    String provideRacerView(List<Racer> racers, int qualifiedRacers);
}
