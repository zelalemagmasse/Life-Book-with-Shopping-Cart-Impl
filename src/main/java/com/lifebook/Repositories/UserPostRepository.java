package com.lifebook.Repositories;

import com.lifebook.Model.UserPost;
import org.springframework.data.repository.CrudRepository;

public interface UserPostRepository extends CrudRepository<UserPost,Long>{
    Iterable<UserPost> findAllByOrderByIdDesc();
    Iterable<UserPost> findAllByContentContains(String query);

}
