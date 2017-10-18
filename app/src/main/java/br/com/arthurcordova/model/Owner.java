package br.com.arthurcordova.model;

import java.io.Serializable;

/**
 * Created by acstapassoli on 16/10/17.
 */

public class Owner implements Serializable {

    private String login;
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
