package com.dimkolya.educationcenter.controller;

import com.dimkolya.educationcenter.dto.UserCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserCredentials());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}