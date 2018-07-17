package com.lifebook.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/")
    public String homePageAdmin() {

        return "index(admin)";
    }

	@RequestMapping("/allmessages")
	public String messages() {

		return "messages";
	}

	@RequestMapping("/allusers")
	public String users() {

		return "users";
	}

	@RequestMapping("/reinstate")
    public String reinstate() {
		return "redirect:/admin/allusers";
	}

    @RequestMapping("/suspend")
    public String suspend() {
        return "redirect:/admin/allusers";
    }

}