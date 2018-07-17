package com.lifebook;

import com.lifebook.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingService {
    @Autowired
    AppUserRepository appUserRepository;


//    public void addFollowIng(String followingName, User currentUser){
//        currentUser.getFollowing().add(followingName);
//
//    }

}
