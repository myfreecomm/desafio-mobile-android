package br.com.andreozawa.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by andre.ozawa on 30/10/2017.
 */

public class RepoOwner implements Serializable {

    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof RepoOwner)) return false;

        RepoOwner repoOwner = (RepoOwner) object;

        return login != null ? login.equals(repoOwner.login) : repoOwner.login == null;

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
