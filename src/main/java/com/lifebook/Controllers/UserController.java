package com.lifebook.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @RequestMapping("/")
    public String homePageLoggedIn() {
        return "index";
    }

	@PostMapping("/newmessage")
	public String sendMessage() {

		// Save message

		return "redirect:/users/";
	}

	@RequestMapping("/profile")
    public String userProfile() {
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