package com.app.githubclient.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thaynan on 17/05/2016.
 */
public class Owner implements Serializable {

    private String avatar_url;
    private String login;

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }
}
