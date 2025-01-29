package com.dimkolya.educationcenter.service;

import com.dimkolya.educationcenter.dto.UserCredentials;
import com.dimkolya.educationcenter.model.User;
import com.dimkolya.educationcenter.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserCredentials userCredentials) {
        if (userRepository.existsByUsername(userCredentials.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(userCredentials.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUsername(userCredentials.getUsername());
        user.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        user.setEmail(userCredentials.getEmail());
        user.setFirstName(userCredentials.getFirstName());
        user.setLastName(userCredentials.getLastName());
        return userRepository.save(user);
    }
}
