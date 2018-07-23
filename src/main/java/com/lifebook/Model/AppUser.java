package com.lifebook.Model;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please provide a password")
    private String password;

    private String profilePic;

    private String fullName;

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    @Column(name = "confirmation_token")
    private String confirmationToken;




    private boolean myFriend=false;

    private int noOfFriend=0;

    public int getNoOfFriend() {
        return noOfFriend;
    }

    public void setNoOfFriend(int noOfFriend) {
        this.noOfFriend = noOfFriend;
    }

    public boolean isMyFriend() {
        return myFriend;
    }

    public void setMyFriend(boolean myFriend) {
        this.myFriend = myFriend;
    }

    public boolean isSuspended() {
        return suspended;
    }

    private String zipCode;



    @OneToMany(mappedBy = "creator")
    private Set<UserPost> posts;

    @OneToMany(mappedBy = "settingUser")
    private Set<Setting> settings;

    @Column(name = "userName", nullable = false, unique = true)
    @NotEmpty(message = "Please provide a username")
    private String username;

    @Column(name = "email", nullable = false,unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    private boolean suspended;





    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AppUser> getFollowing() {
        return following;
    }

    public void setFollowing(Set<AppUser> following) {
        this.following = following;
    }

    public boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> roles;



    @ManyToMany
    private Set<AppUser> following;

    public AppUser() {
        this.roles = new HashSet<>();
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
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }



}
