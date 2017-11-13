package com.innerforge.testapp.repository.detail;

import com.innerforge.testapp.repository.User;

import java.io.Serializable;

/**
 * Created by LuizH on 03/11/2017.
 */

public class PullRequest implements Serializable{

    private String title;
    private User user;
    private String body;
    private String state;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
