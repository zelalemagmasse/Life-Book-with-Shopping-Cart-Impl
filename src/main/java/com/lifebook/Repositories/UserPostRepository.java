package com.lifebook.Repositories;

import com.lifebook.Model.AppUser;
import com.lifebook.Model.AppUserDetails;
import com.lifebook.Model.UserPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

public interface UserPostRepository extends CrudRepository<UserPost,Long>{
    Iterable<UserPost> findAllByOrderByIdDesc();
    Iterable<UserPost> findAllByContentContains(String query);

}
