package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.Person;

public class SourceOfIncomeCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(SourceOfIncomeCalculator.class);

    @Override
    public int calculate(Person person) {

        int points = person.getFinanceData().getSourceOfIncome().size() > 1 ? 100 : 0;
        log.info("Source of income = {} ({} points)", person.getFinanceData().getSourceOfIncome().size(), points);
        return points;
    }
}
