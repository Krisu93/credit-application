package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.Person;

public class IncomeCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(IncomeCalculator.class);

    @Override
    public int calculate(Person person) {

        double incomePerPeople = person.getIncomePerPeople();
        int points = (int) (incomePerPeople / 1000) * 100;
        log.info("Income per family member = {} ({} points)", incomePerPeople, points);
        return points;
    }
}
