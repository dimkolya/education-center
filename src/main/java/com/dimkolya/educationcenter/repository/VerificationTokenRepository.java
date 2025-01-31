package com.dimkolya.educationcenter.repository;

import com.dimkolya.educationcenter.model.User;
import com.dimkolya.educationcenter.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
}
