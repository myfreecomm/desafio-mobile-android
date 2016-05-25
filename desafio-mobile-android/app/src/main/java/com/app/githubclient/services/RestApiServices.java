package com.app.githubclient.services;

import com.app.githubclient.models.Pull;
import com.app.githubclient.models.Repository;
import com.app.githubclient.models.RepositoryListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by thaynan on 18/05/2016.
 */
public interface RestApiServices {

    @GET("repos/{login}/{name}/pulls")
    Call<List<Pull>> getPulls(@Path("login") String login, @Path("name") String name, @Query("page") int page);

    @GET("search/repositories")
    Call<RepositoryListResponse> searchRepos(@Query("q") String query, @Query("sort") String sort, @Query("page") int page);
}