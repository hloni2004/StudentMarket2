package za.ac.student_trade.factory;

import za.ac.student_trade.domain.SuperAdmin;
import za.ac.student_trade.util.Helper;

public class SuperAdminFactory {

    public static SuperAdmin createSuperAdmin(String username, String email, String password) {
        if(Helper.isNullOrEmpty(username) || Helper.isNullOrEmpty(password)){
            return null;
        }
        return new SuperAdmin.Builder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .build();
    }
}