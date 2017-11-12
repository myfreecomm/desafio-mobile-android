package com.nexaas.com.desafio_mobile_android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by marcos_viana on 11/11/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryEntity {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
