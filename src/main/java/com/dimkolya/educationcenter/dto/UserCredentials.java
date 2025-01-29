package com.dimkolya.educationcenter.dto;

import jakarta.validation.constraints.*;

public class UserCredentials {
    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid first name")
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid last name")
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 254)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Only Latin letters and digits expected")
    private String username;

    @NotBlank
    @Size(min = 4, max = 64)
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
