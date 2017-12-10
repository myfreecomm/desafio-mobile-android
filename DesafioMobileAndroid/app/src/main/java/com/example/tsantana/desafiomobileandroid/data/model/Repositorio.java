package com.example.tsantana.desafiomobileandroid.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tsantana on 06/12/2017.
 */

public class Repositorio {
    @SerializedName("name")
    private String nome;

    @SerializedName("description")
    private String descricao;

    @SerializedName("owner")
    private User owner;

    @SerializedName("stargazers_count")
    private Integer stars;
    @SerializedName("forks_count")

    private Integer forks;
    private Integer user_id;

    public Repositorio(){
        this.owner = new User();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
