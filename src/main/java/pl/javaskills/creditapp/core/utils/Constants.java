package pl.javaskills.creditapp.core.utils;

import java.time.ZoneId;
import java.util.Locale;

public interface Constants {
    double MORTGAGE_LOAN_RATE = 0.2;
    double PERSONAL_LOAN_RATE = 0.1;
    double MIN_LOAN_AMOUNT_MORTGAGE = 100000.00;
    ZoneId DEFAULT_SYSTEM_ZONE_ID = ZoneId.of("America/Chicago");
    Locale DEFAULT_SYSTEM_LOCALE = new Locale("en", "US");
    String BIK_API_URI = "http://localhost:8080/udemy/bik/scoring";
    String OUTPUT_PATH = "credits";

    String DOUBLE_REGEX = "(\\d+)(\\.\\d+)?"; //Float values >=0.0
    String INTEGER_REGEX = "(\\d+)"; //or "[0-9]+" - Natural number >=0
    String NAME_REGEX = "([A-Z]{1})+([a-z]{2})+([a-z])?"; //or "[A-ZĄ-Ź][a-zą-ź]{2,9}" - Only letters, first letter must be uppercase, min 3, max 10 characters
    String LASTNAME_REGEX = "(([A-Z]{1})+([a-z])+((-|\\s)([A-Z]{1})+([a-z])+)?)+{6,20}"; //or "([A-ZĄ-Ź][a-zą-ź]+)([\\s-]([A-ZĄ-Ź][a-zą-ź]+))?" - Only letters (max two terms), first letter of term must be uppercase Terms can be separated with space or dash, min 6, max 2 characters
    String MOTHERS_MAIDEN_REGEX = LASTNAME_REGEX;
    String EMAIL_REGEX = "[\\w\\.-]+@+[\\w\\.-]+"; // or ".+@.+" [A-Za-z0-9_] @ [A-Za-z0-9_]
    String PHONE_REGEX = "([+\\d]{3})?[\\d]{9}+";; //or "(\\+\\d{2})?\\d{9}" - 9 digits, with optional country code e.g +48569456456
    String PESEL_REGEX = "[\\d]{11}+";
}
