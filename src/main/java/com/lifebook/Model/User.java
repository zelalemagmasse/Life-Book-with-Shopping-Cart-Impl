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
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please provide a password")
    private String password;
    @Column(name = "userName", nullable = false, unique = true)
    @NotEmpty(message = "Please provide a user Name")
    private String userName;
    private Set<User> followers;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role>role;

    public Set<Setting> getSetting() {
        return setting;
    }

    public void setSetting(Set<Setting> setting) {
        this.setting = setting;
    }

    @ManyToMany(mappedBy = "settingUser")
    private Set<Setting> setting;

    public Set<UserPost> getPosts() {
        return posts;
    }

    public void setPosts(Set<UserPost> posts) {
        this.posts = posts;
    }
    @OneToMany()
    private Set<UserPost>posts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }



    public User() {
        this.followers = new HashSet<>();
        this.role = new HashSet<>();
        this.posts = new HashSet<>();
        this.setting = new HashSet<>();
    }
    public void addRole(Role r){
        role.add(r);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", followers=" + followers +
                ", role=" + role +
                '}';
    }
}
