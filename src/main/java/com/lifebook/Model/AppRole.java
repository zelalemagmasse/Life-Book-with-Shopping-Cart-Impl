package com.lifebook.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "role")
    private List<AppUser> userslist;

    public AppRole() {
    }

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

    public List<AppUser> getUserslist() {
        return userslist;
    }

    public void setUserslist(List<AppUser> userslist) {
        this.userslist = userslist;
    }
}
