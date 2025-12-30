package com.xebec.notes_app.controller;

import com.xebec.notes_app.dto.RegisterDto;
import com.xebec.notes_app.exception.UsernameAlreadyExistsException;
import com.xebec.notes_app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterDto registerDto, Model model) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "auth/register";
        }
        try {
            userService.register(registerDto);
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }

        return "redirect:/login";
    }
}
