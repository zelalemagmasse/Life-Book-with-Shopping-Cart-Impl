package com.lifebook.Model;
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

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> role;

    @ManyToOne
    @OneToOne
    private AppUserDetails details;

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
        this.password = password;
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

    public AppUserDetails getDetails() {
        return details;
    }

    public void setDetails(AppUserDetails details) {
        this.details = details;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
