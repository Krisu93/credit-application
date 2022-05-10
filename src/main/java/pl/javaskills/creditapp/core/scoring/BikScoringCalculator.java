package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.bik.BikApi;
import pl.javaskills.creditapp.core.bik.ScoringRequest;
import pl.javaskills.creditapp.core.bik.ScoringResponse;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;


public class BikScoringCalculator implements ScoringCalculator{
    private static final Logger log = LoggerFactory.getLogger(BikScoringCalculator.class);

    private BikApi bikApi;

    public BikScoringCalculator(){
    }

    public BikScoringCalculator(BikApi bikApi) {
        this.bikApi = bikApi;
    }

    @Override
    public int calculate(CreditApplication application)  {
        Person person = application.getPerson();
        ScoringRequest request = new ScoringRequest();
        if (person instanceof NaturalPerson) {
            request.setPesel(((NaturalPerson) person).getPesel());
        } else if (person instanceof SelfEmployed) {
            request.setNip(((SelfEmployed) person).getNip());
        }

        ScoringResponse response = null;
         response = bikApi.getScoring(request);

        int point = 0;
        if(response != null)
            point = 100 * response.getScoring() / 600;

        log.info("Bik = {}/600, Scoring = {}/100 points", response.getScoring(), point);

        return point;
    }
}
