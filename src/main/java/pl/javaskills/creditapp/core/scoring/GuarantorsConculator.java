package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;

import java.util.Iterator;

public class GuarantorsConculator implements ScoringCalculator {

    private static final Logger log = LoggerFactory.getLogger(GuarantorsConculator.class);

    @Override
    public int calculate(CreditApplication application) {
        int points50 = 0, points25 = 0;
        Iterator<Guarantor> iterator = application.getGuarantors().iterator();
        while (iterator.hasNext()) {
            int p = getPoints(iterator.next());
            if (p == 50)
                points50 = points50 + p;
            else if (p == 25)
                points25 = points25 + p;

        }
        if (points50 > 0)
            log.info("Add points for guarantor with age < 40 = {} points", points50);
        if (points25 > 0)
            log.info("Add points for guarantor with age >= 40 = {} points", points25);
        return points50 + points25;
    }

    private int getPoints(Guarantor guarantor) {
        if (guarantor != null && guarantor.getAge() != null) {
            if (guarantor.getAge() < 40)
                return 50;
            else
                return 25;
        }
        return 0;
    }
}
