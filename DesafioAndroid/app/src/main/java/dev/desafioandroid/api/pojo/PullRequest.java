package dev.desafioandroid.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    @SerializedName("diff_url")
    @Expose
    private String diffUrl;

    @SerializedName("patch_url")
    @Expose
    private String patchUrl;

    @SerializedName("issue_url")
    @Expose
    private String issueUrl;

    @SerializedName("commits_url")
    @Expose
    private String commitsUrl;

    @SerializedName("review_comments_url")
    @Expose
    private String reviewCommentsUrl;

    @SerializedName("review_comment_url")
    @Expose
    private String reviewCommentUrl;

    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;

    @SerializedName("statuses_url")
    @Expose
    private String statusesUrl;

    @SerializedName("number")
    @Expose
    private Long number;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("assignee")
    @Expose
    private Assignee assignee;

    @SerializedName("milestone")
    @Expose
    private Milestone milestone;

    @SerializedName("locked")
    @Expose
    private Boolean locked;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("closed_at")
    @Expose
    private String closedAt;

    @SerializedName("merged_at")
    @Expose
    private String mergedAt;

    @SerializedName("head")
    @Expose
    private Head head;

    @SerializedName("base")
    @Expose
    private Base base;

    @SerializedName("_links")
    @Expose
    private Links links;

    @SerializedName("user")
    @Expose
    private User user;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}