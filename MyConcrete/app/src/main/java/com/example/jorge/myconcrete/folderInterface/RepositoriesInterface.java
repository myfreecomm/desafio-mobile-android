package com.example.jorge.myconcrete.folderInterface;

import com.example.jorge.myconcrete.model.ListWrapper;
import com.example.jorge.myconcrete.model.Repositories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jorge on 09/11/2017.
 */

public interface RepositoriesInterface {
    @GET("/search/repositories")
    Call<ListWrapper<Repositories>> getRepositories(@Query("q") String q, @Query("sort") String sort, @Query("page") String page) ;
}
