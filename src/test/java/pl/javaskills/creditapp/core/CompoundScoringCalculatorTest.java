package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.*;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.core.model.Type.MORTGAGE;

@DisplayName("Testowanie klasy CompoundScoringCalculator")
@ExtendWith(MockitoExtension.class)
class CompoundScoringCalculatorTest {

    private ScoringCalculator scoringCalculator1Mock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator scoringCalculator2Mock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator scoringCalculator3Mock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator scoringCalculator4Mock = Mockito.mock(ScoringCalculator.class);

    private CompoundScoringCalculator calculator = new CompoundScoringCalculator(scoringCalculator1Mock, scoringCalculator2Mock, scoringCalculator3Mock, scoringCalculator4Mock);

    @Test
    @DisplayName("[calculate] spodziewana punktacja = 200")
    public void test1() {

        CreditApplication creditApplication = LoanApplicationTestFactory.create(MORTGAGE, 100000.00, (byte) 25);

        BDDMockito.given(scoringCalculator1Mock.calculate(creditApplication)).willReturn(200);
        BDDMockito.given(scoringCalculator2Mock.calculate(creditApplication)).willReturn(100);
        BDDMockito.given(scoringCalculator3Mock.calculate(creditApplication)).willReturn(-100);
        BDDMockito.given(scoringCalculator4Mock.calculate(creditApplication)).willReturn(0);
        int result = calculator.calculate(creditApplication);
        assertEquals(200, result);
    }

    @Test
    @DisplayName("[calculate] spodziewana punktacja = 500")
    public void test2() {

        CreditApplication creditApplication = LoanApplicationTestFactory.create(MORTGAGE, 100000.00, (byte) 25);

        BDDMockito.given(scoringCalculator1Mock.calculate(creditApplication)).willReturn(400);
        BDDMockito.given(scoringCalculator2Mock.calculate(creditApplication)).willReturn(0);
        BDDMockito.given(scoringCalculator3Mock.calculate(creditApplication)).willReturn(0);
        BDDMockito.given(scoringCalculator4Mock.calculate(creditApplication)).willReturn(100);
        int result = calculator.calculate(creditApplication);
        assertEquals(500, result);
    }

    @Test
    @DisplayName("[calculate] spodziewana punktacja = 100")
    public void test3() {

        CreditApplication creditApplication = LoanApplicationTestFactory.create(MORTGAGE, 100000.00, (byte) 25);


        BDDMockito.given(scoringCalculator1Mock.calculate(creditApplication)).willReturn(300);
        BDDMockito.given(scoringCalculator2Mock.calculate(creditApplication)).willReturn(0);
        BDDMockito.given(scoringCalculator3Mock.calculate(creditApplication)).willReturn(-200);
        BDDMockito.given(scoringCalculator4Mock.calculate(creditApplication)).willReturn(0);
        int result = calculator.calculate(creditApplication);
        assertEquals(100, result);
    }
}