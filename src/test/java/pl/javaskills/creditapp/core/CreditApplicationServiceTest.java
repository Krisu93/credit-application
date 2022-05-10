package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.LoanApplicationTestFactory;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.scoring.ScoringCalculator;
import pl.javaskills.creditapp.core.scoring.PersonScoringCalculatorFactory;
import pl.javaskills.creditapp.core.validation.CreditApplicationValidator;
import pl.javaskills.creditapp.core.validation.post.CompoundPostValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static pl.javaskills.creditapp.core.DecisionType.*;
import static pl.javaskills.creditapp.core.model.Type.MORTGAGE;

@DisplayName("Testowanie klasy CreditApplicationService")
@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {

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

    @BeforeEach //przed odpaleniem każdego testu odpalona będzie ta metoda
    public void init() throws ValidationException, RequirementNotMetException {
        BDDMockito.given(personScoringCalculatorFactory.getCalculator(any(Person.class))).willReturn(calculatorScoringMock);
        BDDMockito.doNothing().when(validatorMock).validate(any(CreditApplication.class));
        BDDMockito.doNothing().when(compoundPostValidatorMock).validate(any(CreditApplication.class), anyInt(), anyDouble());
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - POSITIVE")
    public void test1() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(MORTGAGE, 100000.00, (byte) 25);
        BDDMockito.given(calculatorScoringMock.calculate(creditApplication)).willReturn(450);
        BDDMockito.given(calculatorRatingMock.calculate(creditApplication)).willReturn(110000.00); // rating zamokowany na wartość 110000.00 (kwota ile można dostac kredytu0
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(POSITIVE, decision.getType());
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - NEGATIVE_SCORING")
    public void test2() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(MORTGAGE, 100000.00, (byte) 25);
        BDDMockito.given(calculatorScoringMock.calculate(creditApplication)).willReturn(100);
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(NEGATIVE_SCORING, decision.getType());
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - NEGATIVE_CREDIT_RATING")
    public void test3() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(MORTGAGE, 190000.00, (byte) 25);
        BDDMockito.given(calculatorScoringMock.calculate(creditApplication)).willReturn(450);
        BDDMockito.given(calculatorRatingMock.calculate(creditApplication)).willReturn(180000.00);
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(NEGATIVE_CREDIT_RATING, decision.getType());
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - CONTACT_REQUIRED")
    public void test4() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(MORTGAGE, 100000.00, (byte) 25);
        BDDMockito.given(calculatorScoringMock.calculate(creditApplication)).willReturn(350);
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(CONTACT_REQUIRED, decision.getType());
    }
}