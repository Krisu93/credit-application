package pl.javaskills.creditapp.utils;

import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.IncomeType;
import pl.javaskills.creditapp.core.model.MaritalStatus;
import pl.javaskills.creditapp.core.model.Type;

public class StringCreator {

    private static String PREFIX = "(";
    private static String SUFIX = "):";
    private static String SEPARATOR = ", ";

    public static String generateElements(MaritalStatus... maritalStatus){
        StringBuilder finalString = new StringBuilder(PREFIX);
        for(int i = 0, j = 1; i < maritalStatus.length; i++,j++){
            finalString.append(maritalStatus[i].name()).append(maritalStatus.length > j ? SEPARATOR: SUFIX);
        }
        return finalString.toString();
    }

    public static String generateElements(Education... educations){
        StringBuilder finalString = new StringBuilder(PREFIX);
        for(int i = 0, j = 1; i < educations.length; i++,j++){
            finalString.append(educations[i].name()).append(educations.length > j ? SEPARATOR: SUFIX);
        }
        return finalString.toString();
    }

    public static String generateElements(IncomeType... incomeTypes){
        StringBuilder finalString = new StringBuilder(PREFIX);
        for(int i = 0, j = 1; i < incomeTypes.length; i++,j++){
            finalString.append(incomeTypes[i].name()).append(incomeTypes.length > j ? SEPARATOR: SUFIX);
        }
        return finalString.toString();
    }

    public static String generateElements(Type... types){
        StringBuilder finalString = new StringBuilder(PREFIX);
        for(int i = 0, j = 1; i < types.length; i++,j++){
            finalString.append(types[i].name()).append(types.length > j ? SEPARATOR: SUFIX);
        }
        return finalString.toString();
    }
}
