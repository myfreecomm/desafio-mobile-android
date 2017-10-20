package fabio.oliveira.desafiomobileandroid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by fabio on 18/10/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends RealmObject implements Serializable {

    @JsonProperty("login")
    private String login;
    @JsonProperty("avatar_url")
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
}
