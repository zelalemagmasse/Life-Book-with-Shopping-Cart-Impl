package com.lifebook.Service;

import com.lifebook.Model.AppRole;
import com.lifebook.Model.AppUser;
import com.lifebook.Repositories.AppRoleRepository;
import com.lifebook.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
