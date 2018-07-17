package com.lifebook.Model;

import com.lifebook.Model.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(unique = true)
    private String role;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUserslist() {
        return userslist;
    }

    public void setUserslist(List<User> userslist) {
        this.userslist = userslist;
    }
    @ManyToMany(mappedBy = "role")
    private List<User> userslist;
    public Role() {
    }
}
