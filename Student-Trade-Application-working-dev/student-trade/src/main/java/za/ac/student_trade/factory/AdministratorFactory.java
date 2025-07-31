package za.ac.student_trade.factory;

public class AdministratorFactory {
    public static Administrator createAdministrator(String username, String email, String password) {

        Long adminId = Long.valueOf(Helper.generateId());

        if(Helper.isValidEmail(email) || Helper.isNullOrEmpty(username) || Helper.isNullOrEmpty(password)){
            return new Administrator.Builder()
                    .setAdminId(adminId)
                    .setUsername(username)
                    .setEmail(email)
                    .setPassword(password)
                    .build();
        }
        return null;
    }
}
