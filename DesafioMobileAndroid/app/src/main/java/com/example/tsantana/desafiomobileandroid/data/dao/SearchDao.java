package com.example.tsantana.desafiomobileandroid.data.dao;

import com.example.tsantana.desafiomobileandroid.data.ApiClient;
import com.example.tsantana.desafiomobileandroid.data.model.Repositorio;
import com.google.gson.Gson;

import com.example.tsantana.desafiomobileandroid.data.model.Search;

import java.util.ArrayList;

/**
 * Created by tsantana on 07/12/2017.
 */

public class SearchDao {
    public Search getSearchOfReposByPage(Integer page){
        ApiClient apiClient = new ApiClient("https://api.github.com/search/repositories?q=language:Java&sort=stars&page="+page+"&per_page=30");
        String response = apiClient.getJson();

        Gson gson = new Gson();
        Search pesquisa = gson.fromJson(response, Search.class);

        return ((pesquisa == null)  )? new Search() : pesquisa;
    }
}
