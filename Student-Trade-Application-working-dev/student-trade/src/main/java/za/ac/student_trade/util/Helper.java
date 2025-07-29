package za.ac.student_trade.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.UUID;

public class Helper {

    public static String generateId(){return UUID.randomUUID().toString();}

    //Todo: Validate Email

    public static boolean isValidEmail(String email){
        return  EmailValidator.getInstance().isValid(email);
    }

    //Todo: Validate Price > 0
    //Todo: firstName, lastName, email, and password is not Null

    public static boolean isNullOrEmpty(String s){
        if(s.isEmpty() || s == null){
            return true;
        }
        return false;
    }
    //Todo: validate postal code > 999 && postal code < 10000
    public static boolean postalCodeValid(int postalCode){
        if(postalCode > 999 || postalCode < 1000){
            return true;
        }
        return false;
    }

}
