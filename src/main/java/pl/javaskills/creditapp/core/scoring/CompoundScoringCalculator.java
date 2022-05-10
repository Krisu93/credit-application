package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;

public class CompoundScoringCalculator implements ScoringCalculator {

    private static final Logger log = LoggerFactory.getLogger(CompoundScoringCalculator.class);
    private final ScoringCalculator[] scoringCalculators;

    public CompoundScoringCalculator(ScoringCalculator... scoringCalculators) {
        this.scoringCalculators = scoringCalculators;
    }

    @Override
    public int calculate(CreditApplication person) {
        int scoring = 0;

        for (ScoringCalculator scoringCalculator : scoringCalculators){
            scoring += scoringCalculator.calculate(person);
        }
        log.info("Calculated scoring = {} points.", scoring);

        return scoring;
    }
}
