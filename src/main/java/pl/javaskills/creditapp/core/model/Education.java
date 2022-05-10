package pl.javaskills.creditapp.core.model;

public enum Education {
    PRIMARY(-100),
    MIDDLE(0),
    SECONDARY(0),
    POST_SECONDARY(0),
    TERTIARY(100),
    NONE(-200);

    private int scoringPoints;

    Education(int scoringPoints){
        this.scoringPoints = scoringPoints;
    }

    public int getScoringPoints() {
        return scoringPoints;
    }
}
