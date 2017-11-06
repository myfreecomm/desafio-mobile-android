package br.com.nexas.appgithubjava.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by user on 02/11/2017.
 */

public class Repositorio implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer idRepositorio;
    @SerializedName("name")
    @Expose
    private String nomeRepositorio;
    @SerializedName("description")
    @Expose
    private String descRepositorio;
    @SerializedName("stargazers_count")
    @Expose
    private Integer    numeroEstrelas;
    @SerializedName("forks_count")
    @Expose
    private Integer    numeroForks;
    @SerializedName("owner")
    @Expose
    private User   owner;


    public Repositorio(Integer repositoryId, String name, String description, User owner,
                      Integer starsCount, Integer numForks) {
           this.idRepositorio = repositoryId;
           this.nomeRepositorio = name;
           this.descRepositorio = description;
           this.owner = owner;
           this.numeroEstrelas = starsCount;
           this.numeroForks = numForks;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(Integer idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public String getNomeRepositorio() {
        return nomeRepositorio;
    }

    public void setNomeRepositorio(String nomeRepositorio) {
        this.nomeRepositorio = nomeRepositorio;
    }

    public String getDescRepositorio() {
        return descRepositorio;
    }

    public void setDescRepositorio(String descRepositorio) {
        this.descRepositorio = descRepositorio;
    }

    public Integer getNumeroEstrelas() {
        return numeroEstrelas;
    }

    public void setNumeroEstrelas(Integer numeroEstrelas) {
        this.numeroEstrelas = numeroEstrelas;
    }

    public Integer getNumeroForks() {
        return numeroForks;
    }

    public void setNumeroForks(Integer numeroForks) {
        this.numeroForks = numeroForks;
    }
}
