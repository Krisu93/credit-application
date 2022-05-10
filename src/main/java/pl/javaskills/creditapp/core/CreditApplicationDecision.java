package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.PersonalData;

import java.util.Optional;

public class CreditApplicationDecision {

    private final DecisionType type;
    private final Optional<RequirementNotMetException.Cause> exceptionCause;
    private final PersonalData personalData;
    private final Double rate;
    private final int scoring;

    public CreditApplicationDecision(DecisionType type, PersonalData personalData, Double rate, int scoring) {
        this.type = type;
        this.personalData = personalData;
        this.rate = rate;
        this.scoring = scoring;
        this.exceptionCause = Optional.empty();
    }

    public CreditApplicationDecision(DecisionType type, PersonalData personalData, int scoring) {
        this.type = type;
        this.personalData = personalData;
        this.scoring = scoring;
        this.rate = null;
        this.exceptionCause = Optional.empty();
    }

    public CreditApplicationDecision(DecisionType type, PersonalData personalData, Double rate, int scoring, RequirementNotMetException.Cause exceptionCause) {
        this.type = type;
        this.personalData = personalData;
        this.rate = rate;
        this.scoring = scoring;
        this.exceptionCause = Optional.of(exceptionCause);
    }

    public DecisionType getType(){
        return type;
    }

    public int getScoring() {
        return scoring;
    }

    public Double getRate() {
        return rate;
    }

    public Optional<RequirementNotMetException.Cause> getExceptionCause() {
        return exceptionCause;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
