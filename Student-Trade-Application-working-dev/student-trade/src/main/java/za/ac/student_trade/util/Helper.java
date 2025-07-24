package za.ac.student_trade.util;

import org.apache.commons.validator.routines.EmailValidator;

public class Helper {

    public static boolean ValidString(String str) {
        return str != null && !str.isEmpty();
    }
    public static boolean ValidInteger(Integer i) {
        return i != null && i > 0;
    }
    public static boolean validEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return  validator.isValid(email);
    }
    public static boolean validLong(long value) {
        return value > 0;
    }
}
