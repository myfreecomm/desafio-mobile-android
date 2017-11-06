package br.com.nexas.appgithubjava.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by user on 03/11/2017.
 */

public class User implements Serializable{
    @SerializedName("login")
    @Expose
    private String username;
    @SerializedName("avatar_url")
    @Expose
    private String photo;
    @SerializedName("name")
    @Expose
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

