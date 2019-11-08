package com.example.tsantana.desafiomobileandroid.data.model;

import com.example.tsantana.desafiomobileandroid.utils.CommonUtils;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by tsantana on 06/12/2017.
 */

public class PullRequest {
    @SerializedName("user")
    private User autor;

    @SerializedName("title")
    private String titulo;

    @SerializedName("created_at")
    private Date data;

    @SerializedName("body")
    private String body;

    @SerializedName("html_url")
    private String url;

    private String dataFormatada;

    public PullRequest(){
        this.autor = new User();
    }
    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataFormatada() {
        if(this.data !=null){
            dataFormatada = CommonUtils.dataFormatadaPtBr(this.data);
        }else {
            dataFormatada ="";
        }

        return dataFormatada;
    }

    public void setDataFormatada(String dataFormata) {
        this.dataFormatada = dataFormata;
    }
}
