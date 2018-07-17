package com.lifebook.Model;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please provide a password")
    private String password;
    @Column(name = "userName", nullable = false, unique = true)
    @NotEmpty(message = "Please provide a user Name")
    private String userName;
    @ManyToMany()
    private Set<Following> following;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role>role;

    @OneToOne(mappedBy = "user")
    private AppUserDetails appUserDetails;

    @ManyToMany(mappedBy = "settingUser")
    private Set<Setting> setting;

    public User() {
        this.following = new HashSet<>();
        this.role = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Following> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Following> following) {
        this.following = following;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<Setting> getSetting() {
        return setting;
    }

    public void setSetting(Set<Setting> setting) {
        this.setting = setting;
    }
}
