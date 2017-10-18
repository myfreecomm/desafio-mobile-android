package br.com.arthurcordova.service;

import br.com.arthurcordova.model.GithubRepositoryModel;
import br.com.arthurcordova.model.PullRequestModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by acstapassoli on 16/10/17.
 */

public interface GithubService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    Call<GithubRepositoryModel> getGithubRepositories();

    @GET("/{url}")
    Call<PullRequestModel> getPulls(@Path("url") String url);

}
