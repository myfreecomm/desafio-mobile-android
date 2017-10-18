package br.com.arthurcordova.model;

import java.io.Serializable;

/**
 * Created by acstapassoli on 17/10/17.
 */

public class User implements Serializable {

    private Integer id;
    private String login;
    private String avatar_url;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
