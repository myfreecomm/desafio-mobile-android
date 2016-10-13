package dev.desafioandroid.api.service;

import dev.desafioandroid.api.pojo.GitHubInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositoriesService {

    @GET("search/repositories")
    Call<GitHubInfo> getRepositories(@Query("q") String lang,
                                     @Query("sort") String sort,
                                     @Query("page") int page);
}