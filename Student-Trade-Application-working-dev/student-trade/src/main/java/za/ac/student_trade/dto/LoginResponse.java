package za.ac.student_trade.dto;

public class LoginResponse {
    private boolean success;
    private String message;
    private String role;
    private String redirectUrl;
    private String userId;
    private String name;

    public LoginResponse() {}

    public LoginResponse(boolean success, String message, String role, String redirectUrl, String userId, String name) {
        this.success = success;
        this.message = message;
        this.role = role;
        this.redirectUrl = redirectUrl;
        this.userId = userId;
        this.name = name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}