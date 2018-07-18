package com.lifebook.Controllers;

import com.lifebook.Model.*;
import com.lifebook.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class HomeController {
    @ Autowired
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
    public String homePage() {
        /*AppRole r = new AppRole();

        r.setRole("USER");
        appRoleRepository.save(r);

        Setting s = new Setting();
        s.setNewsType("Sports");
        settingRepository.save(s);

        AppUser u = new AppUser();
        u.getAppRole().add(r);
        u.setUserName("user");
        u.setPassword("password");
        u.setEmail("user@email.com");
        appUserRepository.save(u);

        AppUserDetails d = new AppUserDetails();
        d.getSettings().add(s);
        d.setCurrentUser(u);
        appUserDetailsRepository.save(d);

        u.setDetails(d);
        appUserRepository.save(u);



        d=appUserDetailsRepository.findById(d.getId()).get();
        d.setCurrentUser(u);
        appUserDetailsRepository.save(d);




        UserPost p = new UserPost();
        p.setContent("Hello LifeBook!");
        p.setCreator(d);
        p.setImageUrl("www.image.img");
        p.setDateOfPost(new Date("01/02/2018"));
        userPostRepository.save(p);

        d.getPosts().add(p);
        appUserDetailsRepository.save(d);
*/
           return "index";
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
	public String registration(Model model){
        model.addAttribute("user", new AppUser());

		return "registration";
	}

	@PostMapping("/register")
	public String completeRegistration(@Valid @ModelAttribute("user") AppUser user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        AppUserDetails detail = user.getDetail();
        appUserDetailsRepository.save(detail);
        appUserRepository.save(user);
		return "login";
	}

}