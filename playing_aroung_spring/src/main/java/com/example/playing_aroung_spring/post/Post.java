package com.example.playing_aroung_spring.post;

import com.example.playing_aroung_spring.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String post;

    public Post(Integer id, User user, String post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public Post() {
    }

    public Post(Integer id, User user) {
        this.id = id;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
