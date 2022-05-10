package pl.javaskills.creditapp.core.scoring;

import pl.javaskills.creditapp.di.Inject;
import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;

public class PersonScoringCalculatorFactory {

    @Inject
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator;
    @Inject
    private IncomeCalculator incomeCalculator;
    @Inject
    private MaritalStatusCalculator maritalStatusCalculator;
    @Inject
    private EducationCalculator educationCalculator;
    @Inject
    private SourceOfIncomeCalculator sourceOfIncomeCalculator;
    @Inject
    private GuarantorsConculator guarantorsConculator;
    @Inject
    private BikScoringCalculator bikScoringCalculator;

    public PersonScoringCalculatorFactory(){

    }

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator, EducationCalculator educationCalculator, SourceOfIncomeCalculator sourceOfIncomeCalculator, GuarantorsConculator guarantorsConculator, BikScoringCalculator bikScoringCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
        this.educationCalculator = educationCalculator;
        this.sourceOfIncomeCalculator = sourceOfIncomeCalculator;
        this.guarantorsConculator = guarantorsConculator;
        this.bikScoringCalculator = bikScoringCalculator;
    }


    public ScoringCalculator getCalculator(Person person){
        if(person instanceof NaturalPerson)
            return new CompoundScoringCalculator(incomeCalculator, maritalStatusCalculator, educationCalculator, sourceOfIncomeCalculator, guarantorsConculator, bikScoringCalculator);
        else if (person instanceof SelfEmployed)
            return new CompoundScoringCalculator(incomeCalculator, maritalStatusCalculator, educationCalculator, sourceOfIncomeCalculator, selfEmployedScoringCalculator, guarantorsConculator, bikScoringCalculator);

        return null;
    }
}
