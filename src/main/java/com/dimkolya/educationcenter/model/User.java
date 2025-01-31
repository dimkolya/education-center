package com.dimkolya.educationcenter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "username")
        },
        indexes = {
                @Index(columnList = "creationTime"),
                @Index(columnList = "username", unique = true),
                @Index(columnList = "email", unique = true),
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid first name")
    @Column(nullable = false)
    private String firstName;

    @Setter
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid last name")
    @Column(nullable = false)
    private String lastName;

    @Setter
    @NotBlank
    @Email
    @Size(max = 254)
    @Column(nullable = false)
    private String email;

    @Setter
    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Only Latin letters and digits expected")
    @Column(nullable = false)
    private String username;

    @Setter
    @NotNull
    @Size(max = 80)
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant creationTime;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRole> roles = new HashSet<>();

    @Setter
    @NotNull
    @Column(nullable = false)
    private boolean enabled = false;

    public User() {
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }

    public boolean hasRole(UserRole role) {
        return roles.contains(role);
    }
}

