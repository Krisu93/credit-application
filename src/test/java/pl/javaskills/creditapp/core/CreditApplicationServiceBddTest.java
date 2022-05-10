package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import pl.javaskills.creditapp.core.bik.BikApi;
import pl.javaskills.creditapp.core.bik.ScoringRequest;
import pl.javaskills.creditapp.core.bik.ScoringResponse;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.*;
import pl.javaskills.creditapp.core.validation.*;
import pl.javaskills.creditapp.core.validation.post.CompoundPostValidator;
import pl.javaskills.creditapp.core.validation.post.ExpensesPostValidator;
import pl.javaskills.creditapp.core.validation.post.PostValidator;
import pl.javaskills.creditapp.core.validation.post.PurposeOfLoanPostValidator;
import pl.javaskills.creditapp.core.validation.reflection.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static pl.javaskills.creditapp.core.DecisionType.*;
import static pl.javaskills.creditapp.core.exception.RequirementNotMetException.Cause.TOO_HIGH_PERSONAL_EXPENSES;
import static pl.javaskills.creditapp.core.model.Education.MIDDLE;
import static pl.javaskills.creditapp.core.model.IncomeType.SELF_EMPLOYMENT;
import static pl.javaskills.creditapp.core.model.MaritalStatus.MARRIED;
import static pl.javaskills.creditapp.core.model.Type.MORTGAGE;

@DisplayName("Testowanie klasy CreditApplicationService - scenariusz 5")
public class CreditApplicationServiceBddTest {

    List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    CreditApplicationValidator validator = new CreditApplicationValidator(new ObjectValidator(classProcessors, fieldProcessors)); PostValidator[] postValidators = {new PurposeOfLoanPostValidator(), new ExpensesPostValidator()};
    BikApi bikApi = Mockito.mock(BikApi.class);
    BikScoringCalculator bikScoringCalculator = new BikScoringCalculator(bikApi);
    PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(new SelfEmployedScoringCalculator(), new IncomeCalculator(), new MaritalStatusCalculator(), new EducationCalculator(), new SourceOfIncomeCalculator(), new GuarantorsConculator(), bikScoringCalculator);
    private CreditApplicationService service =  new CreditApplicationService(personScoringCalculatorFactory, new ApplicationRatingService(), validator, new CompoundPostValidator(postValidators));

    public CreditApplicationServiceBddTest() throws Exception {
    }

    @BeforeEach
    public void init(){
        ScoringResponse response = new ScoringResponse();
        response.setScoring(0);
        BDDMockito.given(bikApi.getScoring(any(ScoringRequest.class))).willReturn(response);
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - NEGATIVE_REQUIREMENTS_NOT_MET, za mała kwota (amount) dla kredytu hipotecznego")
    public void test1() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(
                MARRIED,
                MIDDLE,
                1,
                MORTGAGE,
                50000.00,
                (byte) 30,
                new SourceOfIncome(SELF_EMPLOYMENT, 10000.00));
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(NEGATIVE_REQUIREMENTS_NOT_MET, decision.getType());
        assertEquals(600, decision.getScoring());
        assertEquals(360000.00, decision.getRate());
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - NEGATIVE_SCORING, gdy yearsSinceFounded < 2")
    public void test2() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(
                MARRIED,
                MIDDLE,
                1,
                MORTGAGE,
                500000.00,
                (byte) 30,
                1,
                new SourceOfIncome(SELF_EMPLOYMENT, 7000.00));
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(NEGATIVE_SCORING, decision.getType());
        assertEquals(200, decision.getScoring());
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - CONTACT_REQUIRED, gdy yearsSinceFounded >= 2")
    public void test3() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(
                MARRIED,
                MIDDLE,
                1,
                MORTGAGE,
                500000.00,
                (byte) 30,
                3,
                new SourceOfIncome(SELF_EMPLOYMENT, 7000.00));
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(CONTACT_REQUIRED, decision.getType());
        assertEquals(400, decision.getScoring());
    }

    @Test
    @DisplayName("[getDecision] - typ decyzji - NEGATIVE_REQUIREMENTS_NOT_MET i cause = TOO_HIGH_PERSONAL_EXPENSES")
    public void test4() {
        //given
        CreditApplication creditApplication = LoanApplicationTestFactory.create(
                MARRIED,
                MIDDLE,
                1,
                MORTGAGE,
                500000.00,
                (byte) 30,
                3,
                new Expense[]{
                        new Expense("personal", ExpenseType.PERSONAL, 500.00),
                        new Expense("personal2", ExpenseType.PERSONAL, 750.00)
                },
                new SourceOfIncome(SELF_EMPLOYMENT, 2000.00));
        //when
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        //then
        assertEquals(NEGATIVE_REQUIREMENTS_NOT_MET, decision.getType());
        assertEquals(TOO_HIGH_PERSONAL_EXPENSES, decision.getExceptionCause().get());
    }
}
