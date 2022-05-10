package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.IncomeType;
import pl.javaskills.creditapp.core.model.MaritalStatus;
import pl.javaskills.creditapp.core.model.Type;

public class EnumValidator {

    public static boolean isMaritalStatusInputValue(String value){
        for(MaritalStatus maritalStatus : MaritalStatus.values()){
            if(maritalStatus.name().equals(value))
                return true;
        }
        return false;
    }

    public static boolean isEducationInputValue(String value){
        for(Education education : Education.values()){
            if(education.name().equals(value))
                return true;
        }
        return false;
    }

    public static boolean isIncomeTypeInputValue(String value){
        for(IncomeType incomeType : IncomeType.values()){
            if(incomeType.name().equals(value))
                return true;
        }
        return false;
    }

    public static boolean isTypeInputValue(String value){
        for(Type type : Type.values()){
            if(type.name().equals(value))
                return true;
        }
        return false;
    }
}
