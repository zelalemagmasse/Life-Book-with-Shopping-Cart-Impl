package com.lifebook.Controllers;

import com.lifebook.Model.AppUser;
import com.lifebook.Model.UserPost;
import com.lifebook.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    AppRoleRepository roles;

    @Autowired
    AppUserDetailsRepository details;


    @Autowired
    UserPostRepository posts;

    @Autowired
    SettingRepository settings;

    @Autowired
    AppUserRepository users;

    @RequestMapping("/")
    public String homePageLoggedIn(Authentication authentication) {

        if (users.findByUsername(authentication.getName()).getRoles().contains(roles.findByRole("ADMIN")))
            return "redirect:/admin/";
        else
            return "index";
    }

    @PostMapping("/newmessage")
    public String sendMessage(@ModelAttribute("post") UserPost post, Authentication authentication) {
        post.setCreator(users.findByUsername(authentication.getName()).getDetail());
        posts.save(post);

        return "redirect:/users/";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        AppUser user = users.findByUsername(authentication.getName());
        model.addAttribute("currentuser", user);
        //user.setDetail(new AppUserDetails());
        //Add information for the post form
        UserPost post = new UserPost();
        model.addAttribute("post",post);
        model.addAttribute("posts", posts.findAll());
        return "profile";
    }

    @RequestMapping("/following")
    public String followingUsers() {
        return "following";
    }

    @RequestMapping("/weather")
    public String weather() {
        return "weather";
    }

    @RequestMapping("/news")
    public String news() {
        return "news";
    }
}