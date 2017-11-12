package com.nexaas.com.desafio_mobile_android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRepository {

    //private List<RepositoryEntity>  items;
    private RepositoryEntity[] items;


    public RepositoryEntity[] getItems() {
        return items;
    }

    public void setItems(RepositoryEntity[] items) {
        this.items = items;
    }
}
