package pl.javaskills.creditapp.core.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.javaskills.creditapp.CreditApplicationDecisionFactory;
import pl.javaskills.creditapp.core.ApplicationRatingService;
import pl.javaskills.creditapp.core.CreditApplicationDecision;
import pl.javaskills.creditapp.core.CreditApplicationService;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.LoanApplicationTestFactory;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.ProcessedCreditApplication;
import pl.javaskills.creditapp.core.scoring.PersonScoringCalculatorFactory;
import pl.javaskills.creditapp.core.scoring.ScoringCalculator;
import pl.javaskills.creditapp.core.validation.CreditApplicationValidator;
import pl.javaskills.creditapp.core.validation.post.CompoundPostValidator;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FileManagerTest {

    @InjectMocks
    private CreditApplicationService service;

    @Mock
    private ScoringCalculator calculatorScoringMock;

    @Mock
    private CreditApplicationValidator validatorMock;

    @Mock
    private CompoundPostValidator compoundPostValidatorMock;

    @Mock
    private PersonScoringCalculatorFactory personScoringCalculatorFactory;

    @Mock
    private ApplicationRatingService calculatorRatingMock;

    private CreditApplicationDecisionFactory decisionFactory = new CreditApplicationDecisionFactory();

    @BeforeEach
    public void init() throws ValidationException, RequirementNotMetException {
        BDDMockito.given(personScoringCalculatorFactory.getCalculator(any(Person.class))).willReturn(calculatorScoringMock);
        BDDMockito.doNothing().when(validatorMock).validate(any(CreditApplication.class));
        BDDMockito.doNothing().when(compoundPostValidatorMock).validate(any(CreditApplication.class), anyInt(), anyDouble());
    }

    @Test
    public void write(){
        CreditApplication creditApplication = LoanApplicationTestFactory.create();
        BDDMockito.given(calculatorScoringMock.calculate(creditApplication)).willReturn(450);
        BDDMockito.given(calculatorRatingMock.calculate(creditApplication)).willReturn(110000.00);
        CreditApplicationDecision creditApplicationDecision = service.getDecision(creditApplication);
        String decision = decisionFactory.getDecision(creditApplicationDecision, creditApplication);

        ProcessedCreditApplication processedCreditApplication = new ProcessedCreditApplication(creditApplication, decision);
        FileManager.write(processedCreditApplication);
    }

    @Test
    public void read(){
        FileManager.read("126f7db2-bd6e-4201-aeab-058d10377518");
    }
}