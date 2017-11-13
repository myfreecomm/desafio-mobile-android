package com.innerforge.testapp.repository.list;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuizH on 31/10/2017.
 */

public class RepositoryListResponse implements Serializable{

    @SerializedName("items")
    private List<Repository> repositories;

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public void addRepositories(List<Repository> repositories) {
        if(repositories == null){
            this.repositories = new ArrayList<>();
        }
        this.repositories.addAll(repositories);
    }
}
