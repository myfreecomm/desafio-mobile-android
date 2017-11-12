package com.nexaas.com.desafio_mobile_android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by marcos_viana on 11/11/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryEntity implements Serializable{

    private String name;
    private String full_name;
    private String description;
    private String pulls_url;
    private Integer forks_count;
    private Integer stargazers_count;
    private UserEntity owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPulls_url() {
        return pulls_url;
    }

    public void setPulls_url(String pulls_url) {
        this.pulls_url = pulls_url;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }


    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }


    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }
}
