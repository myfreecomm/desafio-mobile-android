package com.example.tsantana.desafiomobileandroid.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tsantana on 06/12/2017.
 */

public class User {
    @SerializedName("id")
    private Integer id;

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String nomeSobreNome;

    @SerializedName("avatar_url")
    private String foto;

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

    public String getNomeSobreNome() {
        return nomeSobreNome;
    }

    public void setNomeSobreNome(String nomeSobreNome) {
        this.nomeSobreNome = nomeSobreNome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
