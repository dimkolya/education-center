package com.dimkolya.educationcenter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid first name")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid last name")
    private String lastName;

    @NotNull
    @NotBlank
    @Email
    @Size(max = 254)
    @NaturalId
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Only Latin letters and digits expected")
    private String username;

    @NotNull
    @Size(max = 80)
    private String password;

    @NotNull
    @CreationTimestamp
    private Date creationTime;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRole> roles = new HashSet<>();

    @NotNull
    @Column
    private boolean enabled = false;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }

    public boolean hasRole(UserRole role) {
        return roles.contains(role);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}

