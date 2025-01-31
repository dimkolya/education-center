package com.dimkolya.educationcenter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Entity
public class VerificationToken {
    private static final int EXPIRATION_MINUTES = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Setter
    private String token;

    @NotNull
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant expiryDate;

    public VerificationToken() {
    }

    public boolean isExpired() {
        return expiryDate == null
                || Instant.now().isAfter(expiryDate.plus(EXPIRATION_MINUTES, ChronoUnit.MINUTES));
    }
}