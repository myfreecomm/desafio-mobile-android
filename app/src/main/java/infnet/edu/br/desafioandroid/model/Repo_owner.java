package infnet.edu.br.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class Repo_owner {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

}
