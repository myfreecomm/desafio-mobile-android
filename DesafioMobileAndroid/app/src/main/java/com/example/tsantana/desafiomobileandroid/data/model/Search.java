package com.example.tsantana.desafiomobileandroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tsantana on 07/12/2017.
 */

public class Search {
    @SerializedName("total_count")
    private Integer total_count;
    @SerializedName("incomplete_results")
    private Boolean incomplete_results;
    @SerializedName("items")
    private ArrayList<Repositorio> repositorios;

    public Search(){
        this.repositorios = new ArrayList<Repositorio>(){
            @Override
            public Repositorio get(int index) {
                try {
                    return super.get(index);
                } catch (Exception e) {
                    return new Repositorio();
                }
            }
        };
    }
    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<Repositorio> getRepositorios() {
        return repositorios;
    }

    public void setRepositorios(ArrayList<Repositorio> repositorios) {
        this.repositorios = repositorios;
    }
}
