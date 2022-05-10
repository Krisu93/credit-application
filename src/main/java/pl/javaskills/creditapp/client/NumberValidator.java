package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.utils.Constants;

public class NumberValidator {

    public static boolean validateDouble(String input, double min, double max){
        if(input.matches(Constants.DOUBLE_REGEX)){
            double value = Double.parseDouble(input);
            if(value >= min && value <= max)
                return true;
        }
        return false;
    }

    public static boolean validateInteger(String input, int min, int max){
        if(input.matches(Constants.INTEGER_REGEX)){
            int value = Integer.parseInt(input);
            if(value >= min && value <= max)
                return true;
        }
        return false;
    }

    public static boolean validateInteger(String input, int... allowedValues){
        if(input.matches(Constants.INTEGER_REGEX)){
            int value = Integer.parseInt(input);
            for(int alloweValue : allowedValues){
                if(value == alloweValue)
                    return true;
            }
        }
        return false;
    }
}
