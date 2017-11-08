package br.com.andreozawa.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.acl.Owner;
import java.util.Date;

/**
 * Created by andre.ozawa on 08/11/2017.
 */

public class PullRequest implements Serializable {

    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("created_at")
    private Date pullRequestDate;
    @SerializedName("state")
    private String state;
    @SerializedName("user")
    private RepoOwner user;
    @SerializedName("html_url")
    private String pullRequestPage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPullRequestDate() {
        return pullRequestDate;
    }

    public void setPullRequestDate(Date pullRequestDate) {
        this.pullRequestDate = pullRequestDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public RepoOwner getUser() {
        return user;
    }

    public void setUser(RepoOwner user) {
        this.user = user;
    }

    public String getPullRequestPage() {
        return pullRequestPage;
    }

    public void setPullRequestPage(String pullRequestPage) {
        this.pullRequestPage = pullRequestPage;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PullRequest)) return false;

        PullRequest that = (PullRequest) object;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
