package com.app.githubclient.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thaynan on 25/05/2016.
 */
public class RepositoryListResponse implements Serializable {

    @SerializedName("items")
    private List<Repository> repositoryList;

    public List<Repository> getRepositoryList() {
        return repositoryList;
    }
}
