package com.innerforge.testapp.repository.list;

import com.google.gson.annotations.SerializedName;
import com.innerforge.testapp.repository.User;

import java.io.Serializable;

/**
 * Created by LuizH on 31/10/2017.
 */

public class Repository implements Serializable{

    private int id;
    private String name;

    @SerializedName("fullName")
    private String fullName;

    private User owner;

    private String description;

    @SerializedName("stargazers_count")
    private int stargazersCount;

    @SerializedName("forks")
    private int forksCount;

    @SerializedName("pulls_url")
    private String pullsUrl;//: "https://api.github.com/repos/elastic/elasticsearch/pulls{/number}",

    public Repository() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPullsUrl() {
        return pullsUrl.replaceAll("\\[.*\\]", "");
    }
}
