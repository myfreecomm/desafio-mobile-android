package dev.desafioandroid.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Milestone {

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("html_url")
    @Expose
    public String htmlUrl;

    @SerializedName("labels_url")
    @Expose
    public String labelsUrl;

    @SerializedName("id")
    @Expose
    public Long id;

    @SerializedName("number")
    @Expose
    public Long number;

    @SerializedName("state")
    @Expose
    public String state;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("creator")
    @Expose
    public Creator creator;

    @SerializedName("open_issues")
    @Expose
    public Long openIssues;

    @SerializedName("closed_issues")
    @Expose
    public Long closedIssues;

    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @SerializedName("closed_at")
    @Expose
    public String closedAt;

    @SerializedName("due_on")
    @Expose
    public String dueOn;
}