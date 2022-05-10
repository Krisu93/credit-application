package pl.javaskills.bikapi.dto;

public class ScoringResponse {
    private final Integer scoring;

    public ScoringResponse(Integer scoring) {
        this.scoring = scoring;
    }

    public Integer getScoring() {
        return scoring;
    }

}
