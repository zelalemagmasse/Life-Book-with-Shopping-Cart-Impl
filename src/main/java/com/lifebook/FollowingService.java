package com.lifebook;

import com.lifebook.Model.User;
import com.lifebook.Repositories.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Authenticator;

@Service
public class FollowingService {
    @Autowired
    UserRepository userRepository;


    public void addFolloiwng(String followingName, User currentUser){
        User u= userRepository.findByUserName(followingName);
        currentUser.getFollowing().add(u);

    }

}
