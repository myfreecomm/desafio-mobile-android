package com.nexaas.com.desafio_mobile_android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by marcos_viana on 12/11/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity implements Serializable {

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
