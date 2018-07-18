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

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please provide a password")
    private String password;

    @Column(name = "userName", nullable = false, unique = true)
    @NotEmpty(message = "Please provide a username")
    private String userName;



    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> role;

    @ManyToOne
    @OneToOne
    private AppUserDetails detail;

    public AppUser() {
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

        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<AppRole> getAppRole() {
        return role;
    }

    public void setAppRole(Set<AppRole> role) {
        this.role = role;
    }

    public AppUserDetails getDetail() {
        return detail;
    }

    public void setDetail(AppUserDetails detail) {
        this.detail = detail;
    }

}
