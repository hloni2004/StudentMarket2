package za.ac.student_trade.service;

import za.ac.student_trade.dto.LoginRequest;
import za.ac.student_trade.dto.LoginResponse;

public interface AuthenticationService {
    LoginResponse authenticate(LoginRequest loginRequest);
}