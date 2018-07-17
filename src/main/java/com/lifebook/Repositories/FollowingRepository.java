package com.lifebook.Repositories;

import com.lifebook.Model.AppUserDetails;
import com.lifebook.Model.Following;
import org.springframework.data.repository.CrudRepository;

public interface FollowingRepository extends CrudRepository<Following,Long>{
}
