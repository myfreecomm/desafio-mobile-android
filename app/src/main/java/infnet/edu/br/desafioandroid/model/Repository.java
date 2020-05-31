package infnet.edu.br.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class Repository {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("description")
    private String description;

    @SerializedName("forks_count")
    private String forks;

    @SerializedName("stargazers_count")
    private String stars;

    @SerializedName("owner")
    public Repo_owner repo_owners;

    @SerializedName("html_url")
    private String url;


    // GETTER AND SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public String getForks() {
        return forks;
    }

    public String getStars() {
        return stars;
    }

    public String getUrl() {
        return url;
    }
}
