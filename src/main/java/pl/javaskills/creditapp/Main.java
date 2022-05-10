package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.CreditApplicationReader;
import pl.javaskills.creditapp.client.DummyCreditApplicationReader;
import pl.javaskills.creditapp.core.CreditApplicationManager;
import pl.javaskills.creditapp.core.scoring.*;
import pl.javaskills.creditapp.di.ClassInitializer;
import pl.javaskills.creditapp.core.validation.*;
import pl.javaskills.creditapp.core.validation.post.ExpensesPostValidator;
import pl.javaskills.creditapp.core.validation.post.PostValidator;
import pl.javaskills.creditapp.core.validation.post.PurposeOfLoanPostValidator;
import pl.javaskills.creditapp.core.validation.reflection.*;
import pl.javaskills.creditapp.integration.BikApiAdapter;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static pl.javaskills.creditapp.core.utils.Constants.DEFAULT_SYSTEM_LOCALE;
import static pl.javaskills.creditapp.core.utils.Constants.DEFAULT_SYSTEM_ZONE_ID;


public class Main {

    /**
     *
     * @param args
     */

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(DEFAULT_SYSTEM_ZONE_ID));
        Locale.setDefault(DEFAULT_SYSTEM_LOCALE);
    }

    public static void main(String[] args) throws Exception {

        ClassInitializer classInitializer = new ClassInitializer();
        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        CreditApplicationValidator validator = new CreditApplicationValidator(new ObjectValidator(classProcessors, fieldProcessors));
        PostValidator[] postValidators = {new PurposeOfLoanPostValidator(), new ExpensesPostValidator()};
        BikScoringCalculator bikScoringCalculator = new BikScoringCalculator(new BikApiAdapter());

        classInitializer.setInstance(validator);
        classInitializer.setInstance(postValidators);
        classInitializer.setInstance(bikScoringCalculator);

        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);
        CreditApplicationReader reader = new DummyCreditApplicationReader();

        if (args != null && args.length > 0) {
            for (String processId : args)
                manager.loadApplication(processId);
        } else {
            manager.add(reader.read());
            manager.add(reader.read());
            manager.add(reader.read());
            manager.startProcessing();
        }
    }
}

