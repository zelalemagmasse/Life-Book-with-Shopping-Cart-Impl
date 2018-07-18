package com.lifebook.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "details")
    private AppUser currentUser;

    @OneToMany(mappedBy = "details")
    private Set<AppUser> followers;

    @OneToMany(mappedBy = "creator")
    private Set<UserPost> posts;

    @OneToMany(mappedBy = "settingUser")
    private Set<Setting> settings;

    public AppUserDetails() {
        this.followers = new HashSet<>();
        this.posts = new HashSet<>();
        this.settings = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AppUser currentUser) {
        this.currentUser = currentUser;
    }

    public Set<AppUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<AppUser> followers) {
        this.followers = followers;
    }

    public Set<UserPost> getPosts() {
        return posts;
    }

    public void setPosts(Set<UserPost> posts) {
        this.posts = posts;
    }

    public Set<Setting> getSettings() {
        return settings;
    }

    public void setSettings(Set<Setting> settings) {
        this.settings = settings;
    }
}
