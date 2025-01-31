package com.dimkolya.educationcenter.controller;

import com.dimkolya.educationcenter.model.User;
import com.dimkolya.educationcenter.model.VerificationToken;
import com.dimkolya.educationcenter.repository.UserRepository;
import com.dimkolya.educationcenter.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken.isEmpty()) {
            return "Invalid token";
        }

        if (verificationToken.get().isExpired()) {
            return "Token has expired";
        }

        User user = verificationToken.get().getUser();
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.delete(verificationToken.get());
        return "Email verified successfully! You can now login.";
    }
}
