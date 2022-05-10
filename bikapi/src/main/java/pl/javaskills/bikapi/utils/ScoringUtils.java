package pl.javaskills.bikapi.utils;

import static pl.javaskills.bikapi.utils.Constants.REGEX;

public class ScoringUtils {
    public static Integer getRandomScoring(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static boolean validate(String var1){
       return var1.matches(REGEX);
    }
}
