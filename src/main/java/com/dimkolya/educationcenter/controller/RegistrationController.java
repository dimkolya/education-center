package com.dimkolya.educationcenter.controller;

import com.dimkolya.educationcenter.dto.UserCredentials;
import com.dimkolya.educationcenter.service.exceptions.UserRegistrationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import com.dimkolya.educationcenter.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") UserCredentials userCredentials,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(userCredentials);
            redirectAttributes.addFlashAttribute("success",
                    "Registration successful! Please check your email.");
            return "redirect:/login";
        } catch (UserRegistrationException e) {
            result.rejectValue("email", "user.exists", e.getMessage());
            return "register";
        }
    }
}
