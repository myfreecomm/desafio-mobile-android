package br.com.arthurcordova.model;

import java.io.Serializable;

/**
 * Created by acstapassoli on 17/10/17.
 */

public class PullRequestModel implements Serializable{

    private Integer id;
    private String state;
    private String title;
    private String body;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
