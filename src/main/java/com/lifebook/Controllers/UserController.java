package com.lifebook.Controllers;

import com.cloudinary.utils.ObjectUtils;
import com.lifebook.Model.AppUser;
import com.lifebook.Model.AppUserDetails;
import com.lifebook.Model.UserPost;
import com.lifebook.Repositories.*;
import com.lifebook.Service.CloudinaryConfig;
import com.lifebook.Service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    AppRoleRepository roles;

    @Autowired
    AppUserDetailsRepository details;

    @Autowired
    FollowingService followingService;

    @Autowired
    UserPostRepository posts;

    @Autowired
    SettingRepository settings;

    @Autowired
    AppUserRepository users;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String homePageLoggedIn(Authentication auth, Model model) {

        if (users.findByUsername(auth.getName()).getRoles().contains(roles.findByRole("ADMIN"))) {
            return "redirect:/admin/";
        }
        else {
            //Set<AppUser> following = users.findByUsername(auth.getName()).getDetail().getFollowers();
            AppUser sessionUser=users.findByUsername(auth.getName());
            AppUserDetails ud=sessionUser.getDetail();
            Set<AppUser> following=ud.getFollowers();
            Set<UserPost> postscont = new HashSet<>();
           // System.out.println( users.findByUsername(auth.getName()).getDetail().getFollowers());



            for (AppUser u: following) {
                //Set<UserPost>posting=u.getDetail().getPosts();



                    postscont.addAll(u.getDetail().getPosts());
                  //  System.out.println("This are the posts of the people i am following =" + postp.getContent());





            }
//            for(int i=0;i<postscont.size();i++){
//                System.out.println(postscont.get(i).getContent());
//
//            }


            model.addAttribute("posts",postscont );
            return "allposts";

        }
    }

    @PostMapping("/newmessage")
    public String sendMessage(@ModelAttribute("post") UserPost post,
                              @RequestParam("file") MultipartFile file, Authentication authentication) {

        AppUserDetails udetail=users.findByUsername(authentication.getName()).getDetail();
        post.setCreator(udetail);

        if (file.isEmpty()) {
            post.setImageUrl("/img/user.png");
            posts.save(post);
            udetail.getPosts().add(post);
            details.save(udetail);
            return "redirect:/users/profile";
        }
        else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                String uploadedName = (String) uploadResult.get("public_id");

                String transformedImage = cloudc.createUrl(uploadedName);
                post.setImageUrl(transformedImage);
                posts.save(post);
                udetail.getPosts().add(post);
                details.save(udetail);

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/users/profile";
            }
        }

        return "redirect:/users/profile";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        AppUser user = users.findByUsername(authentication.getName());
        model.addAttribute("currentuser", user);
        //user.setDetail(new AppUserDetails());
        //Add information for the post form
        UserPost post = new UserPost();
        model.addAttribute("post",post);
        model.addAttribute("posts", posts.findAllByOrderByIdDesc());
        return "profile";
    }

    @RequestMapping("/detail/{id}")
    public String showJob (@PathVariable("id") long id, Authentication auth,Model model) {

        AppUser userNow = users.findById(id).get();
       // System.out.println(userNow.getUsername());

        //System.out.println(" to be followed =" +detail.getFullName());
        AppUser sessionUser = users.findByUsername(auth.getName());
        //System.out.println("Session User= " + sessionUser.getUsername());

        sessionUser.getDetail().getFollowers().add(userNow);
       // System.out.println(sessionUser.getDetail().getFollowers());
        //System.out.println(" detail.getCurrentUser =" +detail.getCurrentUser().getUsername());
        Set<AppUser> following = sessionUser.getDetail().getFollowers();

        for (AppUser u: following) {
            System.out.println("This are the set of people i am following =" + u.getUsername());

        }

        users.save(sessionUser);
        Set<AppUser> followi = users.findByUsername(auth.getName()).getDetail().getFollowers();
        Set<UserPost> postscont = new HashSet<>();
        for (AppUser u: followi) {
                postscont.addAll(u.getDetail().getPosts());

            }
        model.addAttribute("posts",postscont);
        return "allposts";
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

    @RequestMapping("/findpost")
    public String showResults(HttpServletRequest request, Model model) {
        model.addAttribute("posts", posts.findAllByContentContains(request.getParameter("query")));
        return "results";
    }
}