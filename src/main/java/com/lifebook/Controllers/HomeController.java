package com.lifebook.Controllers;

import com.cloudinary.utils.ObjectUtils;
import com.lifebook.Model.*;
import com.lifebook.Repositories.*;
import com.lifebook.Service.CloudinaryConfig;
import com.lifebook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

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

    @Autowired
    CloudinaryConfig cloudc;


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
            @RequestParam("file") MultipartFile file,
            BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            if (file.isEmpty()) {
                user.getDetail().setProfilePic("/img/user.png");
                userService.saveUser(user);
                return "redirect:/login";
            }
            else {
                try {
                    Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                    String uploadedName = (String) uploadResult.get("public_id");

                    String transformedImage = cloudc.createUrl(uploadedName);
                    user.getDetail().setProfilePic(transformedImage);
                    userService.saveUser(user);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "redirect:/login";
                }
            }
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