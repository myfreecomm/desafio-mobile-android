package br.com.arthurcordova.service;

import java.util.List;

import br.com.arthurcordova.model.GithubRepositoryModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by acstapassoli on 16/10/17.
 */

public interface GithubService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    Call<GithubRepositoryModel> getGithubRepositories();

}
