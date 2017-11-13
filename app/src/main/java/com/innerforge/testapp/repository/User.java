package com.innerforge.testapp.repository;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by LuizH on 31/10/2017.
 */

public class User implements Serializable{

    private String login;

    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
