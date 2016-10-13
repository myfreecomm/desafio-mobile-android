package dev.desafioandroid.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    public Self self;

    @SerializedName("html")
    @Expose
    public Html html;

    @SerializedName("issue")
    @Expose
    public Issue issue;

    @SerializedName("comments")
    @Expose
    public Comments comments;

    @SerializedName("review_comments")
    @Expose
    public ReviewComments reviewComments;

    @SerializedName("review_comment")
    @Expose
    public ReviewComment reviewComment;

    @SerializedName("commits")
    @Expose
    public Commits commits;

    @SerializedName("statuses")
    @Expose
    public Statuses statuses;
}