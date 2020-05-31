package infnet.edu.br.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class User {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("gravatar_id")
    private String gravatarId;

    // GETTERS AND SETTERS

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

}
