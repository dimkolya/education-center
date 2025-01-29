package com.dimkolya.educationcenter.controller;

import com.dimkolya.educationcenter.dto.UserCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.dimkolya.educationcenter.model.User;
import com.dimkolya.educationcenter.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute UserCredentials usercredentials) {
        userService.registerUser(usercredentials);
        return "redirect:/login";
    }
}
