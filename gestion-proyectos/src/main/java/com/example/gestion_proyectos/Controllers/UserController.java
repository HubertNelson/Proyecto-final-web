package com.example.gestion_proyectos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.gestion_proyectos.Services.UserService;
import com.example.gestion_proyectos.models.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.save(user);
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        User foundUser = userService.findByUsername(user.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("user", foundUser);
            return "dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
