package edu.icet.service.impl;

import edu.icet.dto.SignUpRequest;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.repository.UserRepository;
import edu.icet.service.AuthService;
import edu.icet.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public User createCustomer(SignUpRequest signUpRequest) {
        UserEntity userEntity=new UserEntity(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                new BCryptPasswordEncoder().encode(signUpRequest.getPassword()),
                UserRole.CUSTOMER);
        UserEntity createdUser=userRepository.save(userEntity);
        User user=new User();
        user.setId(createdUser.getId());
        return user;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
