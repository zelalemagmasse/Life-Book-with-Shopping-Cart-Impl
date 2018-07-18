package com.lifebook.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "role")
    private Set<AppUser> userslist;

    public AppRole() {
        this.userslist = new HashSet<>();
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

    public Set<AppUser> getUserslist() {
        return userslist;
    }

    public void setUserslist(Set<AppUser> userslist) {
        this.userslist = userslist;
    }
}
