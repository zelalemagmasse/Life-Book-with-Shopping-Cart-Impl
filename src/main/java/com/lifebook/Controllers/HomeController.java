package com.lifebook.Controllers;

import com.lifebook.Model.*;
import com.lifebook.Repositories.*;
import com.lifebook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    AppRoleRepository roles;

    @Autowired
    AppUserDetailsRepository details;

    @Autowired
    UserService userService;

    @Autowired
    UserPostRepository posts;

    @Autowired
    SettingRepository settings;

    @Autowired
    AppUserRepository users;


    @RequestMapping("/")
    public String homePage() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registration(Model model){
        model.addAttribute("user", new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") AppUser user,
            BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "redirect:/login";
    }

    @PostConstruct
    public void loadData() {

        AppRole admin = new AppRole();
        admin.setRole("ADMIN");
        roles.save(admin);

        AppUser adminLogin = new AppUser();
        adminLogin.setUsername("admin");
        adminLogin.setPassword("adminp");
        adminLogin.getRoles().add(admin);
        users.save(adminLogin);

    }
}