package com.lifebook.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage() {

        return "index(anyone)";
    }

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@PostMapping("/login")
	public String loggedIn() {

		return "redirect:/users/";
	}

	@GetMapping("/register")
	public String registration(){

		return "registration";
	}

	@PostMapping("/register")
	public String completeRegistration() {

		// Save new user here

		return "login";
	}

}