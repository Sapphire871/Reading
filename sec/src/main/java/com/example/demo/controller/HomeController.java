package com.example.demo.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.TextComparisonService;

@Controller
public class HomeController {

	private final TextComparisonService comparisonService;

    public HomeController(TextComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

	@GetMapping("/home")
	public String home() {	
		return "home.html";
	}
	
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
	                        Model model) {
	    if (error != null) {
	        model.addAttribute("loginError", "Invalid username or password.");
	    }
	    return "login";
	}
	
	@GetMapping("/logout-success")
	public String logoutPage() {
		return "logout.html";
	}
	
	@PostMapping("/compare")
    public String compareFiles(@RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2,
                               Model model) {
        try {
            String text1 = new String(file1.getBytes(), StandardCharsets.UTF_8);
            String text2 = new String(file2.getBytes(), StandardCharsets.UTF_8);

            String result = comparisonService.compareTexts(text1, text2);
            model.addAttribute("result", result);
        } catch (IOException e) {
            model.addAttribute("result", "Error reading files: " + e.getMessage());
        }

        return "home";
    }
	
	
}
