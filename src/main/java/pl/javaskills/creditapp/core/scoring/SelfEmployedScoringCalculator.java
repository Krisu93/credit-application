package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.SelfEmployed;

public class SelfEmployedScoringCalculator implements ScoringCalculator {

    private static final Logger log = LoggerFactory.getLogger(CompoundScoringCalculator.class);

    @Override
    public int calculate(SelfEmployed selfEmployed) {
        int point = 0;
        point = selfEmployed.getYearsSinceFounded() < 2 ? -200 : 0;

        log.info("Years since founded = {} points.", point);
        return point;
    }
}
