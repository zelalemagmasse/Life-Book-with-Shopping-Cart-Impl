package com.lifebook.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Following {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToMany (mappedBy = "following")
    private Set<User> users;

    @OneToOne()
    AppUserDetails appUserDetails;

    public Following() {
        this.users = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public AppUserDetails getAppUserDetails() {
        return appUserDetails;
    }

    public void setAppUserDetails(AppUserDetails appUserDetails) {
        this.appUserDetails = appUserDetails;
    }
}
