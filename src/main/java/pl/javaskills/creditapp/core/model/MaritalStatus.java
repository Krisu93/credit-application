package pl.javaskills.creditapp.core.model;

public enum MaritalStatus {
    DIVORCED(0),
    SEPARATED(100),
    SINGLE(0),
    WIDOWED(0),
    MARRIED(100);

    private int scoringPoints;

    MaritalStatus(int scoringPoints){
        this.scoringPoints = scoringPoints;
    }

    public int getScoringPoints() {
        return scoringPoints;
    }
}
