package com.example.gestion_proyectos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.gestion_proyectos.Services.ProjectService;

@Controller
public class ViewController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "dashboard";
    }
}
