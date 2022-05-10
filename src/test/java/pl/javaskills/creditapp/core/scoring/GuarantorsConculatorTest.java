package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;
import pl.javaskills.creditapp.core.model.LoanApplicationTestFactory;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.utils.AgeUtils.generateBirthDate;

class GuarantorsConculatorTest {

    private GuarantorsConculator guarantorsConculator = new GuarantorsConculator();

    @Test
    @DisplayName("zwróć punkty względem zbioru poręczycieli")
    public void test(){
        //given
        Guarantor g1 =  Guarantor.Builder.create().withPesel("93070234543").withAge(generateBirthDate(28)).build();
        Guarantor g2 =  Guarantor.Builder.create().withPesel("81081463737").withAge(generateBirthDate(40)).build();
        Guarantor g3 =  Guarantor.Builder.create().withPesel("71031454632").withAge(generateBirthDate(50)).build();
        CreditApplication creditApplication = LoanApplicationTestFactory.create(g1,g2,g3);
        //when
        int point = guarantorsConculator.calculate(creditApplication);
        //then
        assertEquals(100, point);
    }

}