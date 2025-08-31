package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.util.Helper;

public class AdministratorFactory {
    public static Administrator createAdministrator(String username, String email, String password) {

        if(( Helper.isNullOrEmpty(username) || Helper.isNullOrEmpty(password))){
            return null;
        }
        return new Administrator.Builder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .build();

    }
}
