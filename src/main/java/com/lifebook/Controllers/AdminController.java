package com.lifebook.Controllers;

import com.lifebook.Model.AppUser;
import com.lifebook.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AppUserRepository users;

    @RequestMapping("/")
    public String homePageAdmin() {

        return "index";
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
	@RequestMapping("/displayusers")
	public String showAllUsers(Model model) {
		model.addAttribute("users", users.findAll());
		return "displayusers";
	}
	@RequestMapping("/suspend/{userId}")
	public String suspend(@PathVariable String userId, Model model) {
		AppUser user = users.findById(Long.parseLong(userId)).get();
		user.setSuspended(true);
		users.save(user);
		model.addAttribute("users", users.findAll());
		return "displayusers";
	}

	@RequestMapping("/unsuspend/{userId}")
	public String unsuspend(@PathVariable String userId, Model model) {
		AppUser user = users.findById(Long.parseLong(userId)).get();
		user.setSuspended(false);
		users.save(user);
		model.addAttribute("users", users.findAll());
		return "displayusers";
	}

}