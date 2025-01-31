package com.dimkolya.educationcenter.service;

import com.dimkolya.educationcenter.dto.UserCredentials;
import com.dimkolya.educationcenter.model.User;
import com.dimkolya.educationcenter.model.VerificationToken;
import com.dimkolya.educationcenter.repository.UserRepository;
import com.dimkolya.educationcenter.repository.VerificationTokenRepository;
import com.dimkolya.educationcenter.service.exceptions.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;

    public void registerUser(UserCredentials userCredentials) throws UserRegistrationException {
        if (userRepository.existsByUsername(userCredentials.getUsername())) {
            throw new UserRegistrationException("Username already taken");
        }
        Optional<User> userOptional = userRepository.findByEmail(userCredentials.getEmail());
        if (userOptional.isPresent() && userOptional.get().isEnabled()) {
            throw new UserRegistrationException("User with this email already registered");
        }
        User user = new User();
        user.setUsername(userCredentials.getUsername());
        user.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        user.setEmail(userCredentials.getEmail());
        user.setFirstName(userCredentials.getFirstName());
        user.setLastName(userCredentials.getLastName());
        user.setEnabled(false);

        user = userRepository.save(user);
        sendVerificationEmail(user);
    }

    private void sendVerificationEmail(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(UUID.randomUUID().toString());

        verificationTokenRepository.save(verificationToken);
        emailService.sendVerificationEmail(user.getEmail(), verificationToken.getToken());
    }
}
