package br.com.nexas.appgithubjava.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import br.com.nexas.appgithubjava.modelo.Repositorio;

/**
 * Created by user on 02/11/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositorioDTO implements Serializable{


    @SerializedName("items")
    @Expose
    private List<Repositorio> repositorios = null;

    @SerializedName("total_count")
    @Expose
    private Integer numRepositorios;

    public List<Repositorio> getRepositorios() {
        return repositorios;
    }

    public void setRepositorios(List<Repositorio> repositorios) {
          this.repositorios = repositorios;
    }

    public Integer getNumRepositorios() {
            return numRepositorios;
    }

    public void setNumRepositorios(Integer numRepositorios) {
         this.numRepositorios = numRepositorios;
    }

}
