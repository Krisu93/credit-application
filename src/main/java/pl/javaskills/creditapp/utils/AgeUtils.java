package pl.javaskills.creditapp.utils;

import java.time.LocalDate;

public class AgeUtils {
    public static LocalDate generateBirthDate(int expAge){
        return LocalDate.now().minusYears(expAge).minusDays(1);
    }

}
