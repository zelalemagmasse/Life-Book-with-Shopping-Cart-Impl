package com.lifebook.Repositories;

import com.lifebook.Model.User;
import com.lifebook.Model.UserPost;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
    User findByUserName(String  userName);

}
