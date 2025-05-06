package com.example.authdemo.controller;

import com.example.authdemo.dto.LoginRequest;
import com.example.authdemo.dto.RegisterRequest;
import com.example.authdemo.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) boolean error,
                              @RequestParam(required = false) boolean registered,
                              Model model) {
        if (error) model.addAttribute("error", "Invalid credentials");
        if (registered) model.addAttribute("success", "Registration successful!");
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginRequest loginRequest,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        var response = authService.login(loginRequest);
        if (response.token() == null) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/auth/login";
        }
        
        session.setAttribute("token", response.token());
        session.setAttribute("username", response.username());
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute RegisterRequest registerRequest,
                                RedirectAttributes redirectAttributes) {
        var response = authService.register(registerRequest);
        if (response.message().equals("Username already exists")) {
            redirectAttributes.addFlashAttribute("error", response.message());
            return "redirect:/auth/register";
        }
        return "redirect:/auth/login?registered=true";
    }

    @GetMapping("/logout")
    public String processLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}