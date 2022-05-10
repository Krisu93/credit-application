package pl.javaskills.creditapp.core.scoring;

import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;


public interface ScoringCalculator {

    default int calculate(CreditApplication creditApplication) {
        return calculate(creditApplication.getPerson());
    }

    default int calculate(Person person) {
        if (person instanceof SelfEmployed)
            return calculate((SelfEmployed) person);
        else if (person instanceof NaturalPerson)
            return calculate((NaturalPerson) person);
        return 0;
    }

    default int calculate(SelfEmployed selfEmployed) {
        return 0;
    }

    default int calculate(NaturalPerson naturalPerson) {
        return 0;
    }
}
