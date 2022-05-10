package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.Person;

public class EducationCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(EducationCalculator.class);

    @Override
    public int calculate(Person person) {

        int points = person.getPersonalData().getEducation().getScoringPoints();
        log.info("Education = {} ({} points)", person.getPersonalData().getEducation(), points);
        return person.getPersonalData().getEducation().getScoringPoints();
    }
}
