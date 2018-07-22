package com.lifebook.Service;

import com.lifebook.Repositories.AppUserRepository;
import com.lifebook.Repositories.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingService {
    @Autowired
    UserPostRepository posts;

    @Autowired
    AppUserRepository users;

    /*public Set<UserPost> getFollowingAndMyPosts(Authentication auth) {
        Set<UserPost> manyPosts = new HashSet<>();
        Set<AppUser> following = users.findByUsername(auth.getName()).getDetail().getFollowers();

        for (AppUser user : following) {
            manyPosts.add(posts.findByCreatorContains(user.getDetail()));
        }

        return manyPosts;
    }
*/
}
