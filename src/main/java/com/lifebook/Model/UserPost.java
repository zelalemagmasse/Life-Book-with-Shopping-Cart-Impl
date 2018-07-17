package com.lifebook.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String subject;
    private String content;
    private String imageUrl;
    private Date dateOfPost;

    @ManyToOne
    private AppUserDetails creator;


    public UserPost() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDateOfPost() {
        return dateOfPost;
    }

    public void setDateOfPost(Date dateOfPost) {
        this.dateOfPost = dateOfPost;
    }

    public AppUserDetails getCreator() {
        return creator;
    }

    public void setCreator(AppUserDetails creator) {
        this.creator = creator;
    }
}
