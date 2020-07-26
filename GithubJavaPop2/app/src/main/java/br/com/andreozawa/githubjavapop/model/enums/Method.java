package br.com.andreozawa.githubjavapop.model.enums;

/**
 * Created by andre.ozawa on 30/10/2017.
 */

public enum Method {
    GET("GET"),
    PATCH("PATCH"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String description;

    Method(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
