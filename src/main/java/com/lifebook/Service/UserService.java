package com.lifebook.Service;

import com.lifebook.Model.AppRole;
import com.lifebook.Model.AppUser;
import com.lifebook.Model.AppUserDetails;
import com.lifebook.Repositories.AppRoleRepository;
import com.lifebook.Repositories.AppUserDetailsRepository;
import com.lifebook.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    AppUserDetailsRepository details;

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

        AppRole userRole = new AppRole();
        userRole.setRole("USER");
        roles.save(userRole);

        user.getRoles().add(userRole);

        AppUserDetails detail = user.getDetail();
        details.save(detail);
        users.save(user);
    }
}
