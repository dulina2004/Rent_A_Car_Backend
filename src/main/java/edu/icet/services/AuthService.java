package edu.icet.services;
import edu.icet.dto.SignUpRequest;
import edu.icet.dto.User;

public interface AuthService {
    User createCustomer(SignUpRequest signUpRequest);
    boolean hasCustomerWithEmail(String email);
}
