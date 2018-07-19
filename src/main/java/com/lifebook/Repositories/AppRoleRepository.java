package com.lifebook.Repositories;

import com.lifebook.Model.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole,Long>{
    AppRole findByRole(String role);
}
