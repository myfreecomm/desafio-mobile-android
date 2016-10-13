package dev.desafioandroid.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Base {

    @SerializedName("label")
    @Expose
    public String label;

    @SerializedName("ref")
    @Expose
    public String ref;

    @SerializedName("sha")
    @Expose
    public String sha;

    @SerializedName("user")
    @Expose
    public User user;

    @SerializedName("repo")
    @Expose
    public Repo repo;
}