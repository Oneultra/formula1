package org.pavelkonrashov.formula1.domain;

public class RaceData {
    private final String startLogFile;
    private final String endLogFile;
    private final String abbreviationsTxtFile;
    private final int qualifiedRacers;

    private RaceData(Builder builder) {
        startLogFile = builder.startLogFile;
        endLogFile = builder.endLogFile;
        abbreviationsTxtFile = builder.abbreviationTxtFile;
        qualifiedRacers = builder.qualifiedRacers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getStartLogFile() {
        return startLogFile;
    }

    public String getEndLogFile() {
        return endLogFile;
    }

    public String getAbbreviationsTxtFile() {
        return abbreviationsTxtFile;
    }

    public int getQualifiedRacers() {
        return qualifiedRacers;
    }

    public static class Builder {
        private String startLogFile;
        private String endLogFile;
        private String abbreviationTxtFile;
        private int qualifiedRacers;

        private Builder() {

        }

        public Builder withStartLogFile(String startLogFile) {
            this.startLogFile = startLogFile;
            return this;
        }

        public Builder withEndLogFile(String endLogFile) {
            this.endLogFile = endLogFile;
            return this;
        }

        public Builder withAbbreviationTxtFile(String abbreviationTxtFile) {
            this.abbreviationTxtFile = abbreviationTxtFile;
            return this;
        }

        public Builder withQualifiedRacers(int qualifiedRacers) {
            this.qualifiedRacers = qualifiedRacers;
            return this;
        }

        public RaceData build() {
            return new RaceData(this);
        }
    }
}
