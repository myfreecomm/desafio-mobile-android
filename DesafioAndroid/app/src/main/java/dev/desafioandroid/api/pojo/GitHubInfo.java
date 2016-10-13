package dev.desafioandroid.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GitHubInfo {

    @SerializedName("total_count")
    @Expose
    private Long totalCount;

    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;

    @SerializedName("items")
    @Expose
    private List<Repo> items = new ArrayList<>();

    public List<Repo> getItems() {
        return items;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public Long getTotalCount() {
        return totalCount;
    }
}