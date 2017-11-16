package br.com.desafioandroid.popularrepoapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dennys on 15/11/2017.
 */

public class PullRequestEntity {

    @SerializedName("title")
    public String titlePullRequest;

    @SerializedName("user")
    public UserEntity userEntity;

    @SerializedName("created_at")
    public String datePullRequest;

    @SerializedName("body")
    public String bodyPullRequest;

    @SerializedName("state")
    public String state;

    @SerializedName("html_url")
    public String url;
}
