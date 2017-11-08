package br.com.andreozawa.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by andre.ozawa on 30/10/2017.
 */

public class PublicRepos implements Serializable {

    @SerializedName("")
    private int id;
    @SerializedName("id")
    private int idRepo;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("forks_count")
    private int qtForks;
    @SerializedName("stargazers_count")
    private int qtStars;
    @SerializedName("owner")
    private RepoOwner owner;

    public PublicRepos() {
    }

    public PublicRepos(int id, int idRepo, String name, String description, int qtForks, int qtStars) {
        this.id = id;
        this.idRepo = idRepo;
        this.name = name;
        this.description = description;
        this.qtForks = qtForks;
        this.qtStars = qtStars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRepo() {
        return idRepo;
    }

    public void setIdRepo(int idRepo) {
        this.idRepo = idRepo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtForks() {
        return qtForks;
    }

    public void setQtForks(int qtForks) {
        this.qtForks = qtForks;
    }

    public int getQtStars() {
        return qtStars;
    }

    public void setQtStars(int qtStars) {
        this.qtStars = qtStars;
    }

    public RepoOwner getOwner() {
        return owner;
    }

    public void setOwner(RepoOwner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PublicRepos)) return false;

        PublicRepos that = (PublicRepos) object;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
