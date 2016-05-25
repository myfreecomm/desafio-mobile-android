package com.app.githubclient.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thaynan on 18/05/2016.
 */
public class Pull implements Serializable {
    private String title;
    private String body;
    private Owner user;
    @SerializedName("created_at")
    private String created;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Owner getUser() {
        return user;
    }

    public String getCreated() {
        return created;
    }
}
