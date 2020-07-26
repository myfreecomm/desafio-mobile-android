package com.nexaas.com.desafio_mobile_android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by marcos_viana on 11/11/17.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequestEntity {

    private String title;
    private String body;
    private UserEntity user;
    private String created_at;
    private String html_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
