package com.app.githubclient.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thaynan on 17/05/2016.
 */
public class Repository implements Serializable {

    private String name;
    private String description;
    @SerializedName("forks_count")
    private int forks;
    @SerializedName("stargazers_count")
    private int stars;
    private Owner owner;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getForks() {
        return forks;
    }

    public int getStars() {
        return stars;
    }

    public Owner getOwner() {
        return owner;
    }
}
