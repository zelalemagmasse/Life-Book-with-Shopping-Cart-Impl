//package com.lifebook.Model;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//public class Follower {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private long id;
//
//    @OneToMany(mappedBy = "follower")
//    private Set<AppUser> followers = new HashSet<>();
//
//    private String allFollowers;
//
//    public Follower() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Set<AppUser> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(Set<AppUser> followers) {
//        this.followers = followers;
//    }
//
//    public String getAllFollowers() {
//        return allFollowers;
//    }
//
//    public void setAllFollowers(String allFollowers) {
//        this.allFollowers = allFollowers;
//    }
//
//
//}
