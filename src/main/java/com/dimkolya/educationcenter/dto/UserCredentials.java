package com.dimkolya.educationcenter.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCredentials {
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid first name")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^\\p{L}+(?:[\\p{L}\\s'-]*\\p{L})?$", message = "Invalid last name")
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 254)
    private String email;

    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Only Latin letters and digits expected")
    private String username;

    @NotBlank
    @Size(min = 4, max = 64)
    private String password;
}
