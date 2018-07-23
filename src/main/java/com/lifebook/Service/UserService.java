package com.lifebook.Service;

import com.lifebook.Model.AppRole;
import com.lifebook.Model.AppUser;
import com.lifebook.Repositories.AppRoleRepository;
import com.lifebook.Repositories.AppUserRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {


    @Autowired
    AppUserRepository users;

    @Autowired
    AppRoleRepository roles;

    @Autowired
    public UserService(AppUserRepository users) {
        this.users = users;
    }

    public AppUser findByUserName(String username) {
        return users.findByUsername(username);
    }

    public void saveUser(AppUser user) {

        user.getRoles().add(roles.findByRole("USER"));
        users.save(user);
    }

    public AppUser findByEmail(String email) {
        return users.findByEmail(email);
    }

    public AppUser findByConfirmationToken(String confirmationToken) {
        return users.findByConfirmationToken(confirmationToken);
    }

//    public void setCategories (AppUser user, javax.servlet.http.HttpServletRequest request){
//        ArrayList<String> interests = new ArrayList<>();
//
//        if (request.getParameter("business")!=null &&request.getParameter("business") .equals("business")) {
//            interests.add("business");
//        }
//        if (request.getParameter("science").equals("science")) {
//            interests.add("science");
//        }
//        if (request.getParameter("health").equals("health")) {
//            interests.add("health");
//        }
//        if (request.getParameter("sports").equals("sports")) {
//            interests.add("sports");
//        }
//        if (request.getParameter("entertainment").equals("entertainment")) {
//            interests.add("entertainment");
//        }
//        if (request.getParameter("general").equals("general")) {
//            interests.add("general");
//        }
//        user.setInterests(interests);
//        users.save(user);
//
//            for (String interest: user.getInterests()){
//                System.out.println(interest);
//            }
        }
