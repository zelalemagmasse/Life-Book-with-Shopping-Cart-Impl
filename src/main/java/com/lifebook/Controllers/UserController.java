package com.lifebook.Controllers;

import com.lifebook.Model.AppUser;
import com.lifebook.Model.AppUserDetails;
import com.lifebook.Model.UserPost;
import com.lifebook.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserDetailsRepository appUserDetailsRepository;


    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    AppUserRepository appUserRepository;



    @RequestMapping("/")
    public String homePageLoggedIn() {
        return "index";
    }

	@PostMapping("/newmessage")
	public String sendMessage(@ModelAttribute("post") UserPost post) {
        userPostRepository.save(post);

		return "redirect:/users/";
	}

	@RequestMapping("/profile")
    public String userProfile(Model model) {
        AppUser user = appUserRepository.findByUserName("u");
        model.addAttribute("currentuser", user);
        user.setDetail(new AppUserDetails());
        //Add information for the post form
        UserPost post = new UserPost();
        post.setCreator(appUserRepository.findByUserName(user.getUserName()).getDetail());
        model.addAttribute("post",post);
		return "profile";
	}

	@RequestMapping("/following")
    public String follwoingUsers() {
		return "following";
	}

	@RequestMapping("/weather")
    public String weather() {
		return "weather";
	}

	@RequestMapping("/news")
    public String news() {
		return "weather";
	}


}