package org.pavelkonrashov.formula1.domain;

import java.time.Duration;
import java.util.Objects;

public class Racer {
    private final String name;
    private final String team;
    private final String abbreviation;
    private final Duration bestTime;

    private Racer(Builder builder) {
        name = builder.name;
        team = builder.team;
        abbreviation = builder.abbreviation;
        bestTime = builder.bestTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Duration getBestTime() {
        return bestTime;
    }

    @Override
    public String toString() {
        return "Racer: " +
                "name = '" + name + '\'' +
                ", team = '" + team + '\'' +
                ", abbreviation = '" + abbreviation + '\'' +
                ", bestTime = " + bestTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Racer racer = (Racer) o;
        return Objects.equals(name, racer.name) &&
                Objects.equals(team, racer.team) &&
                Objects.equals(abbreviation, racer.abbreviation) &&
                Objects.equals(bestTime, racer.bestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, abbreviation, bestTime);
    }

    public static class Builder {
        private String name;
        private String team;
        private String abbreviation;
        private Duration bestTime;

        private Builder() {

        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTeam(String team) {
            this.team = team;
            return this;
        }

        public Builder withAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
            return this;
        }

        public Builder withBestTime(Duration bestTime) {
            this.bestTime = bestTime;
            return this;
        }

        public Racer build() {
            return new Racer(this);
        }
    }
}
