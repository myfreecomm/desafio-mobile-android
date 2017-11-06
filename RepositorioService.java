package br.com.nexas.appgithubjava.service;

import java.util.List;

import br.com.nexas.appgithubjava.dto.RepositorioDTO;
import br.com.nexas.appgithubjava.modelo.PullRequest;
import br.com.nexas.appgithubjava.modelo.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 02/11/2017.
 */

public interface RepositorioService {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<RepositorioDTO> getRepositorio(@Query("page") Integer page);

    @GET("repos/{user}/{repo}/pulls")
    Call<List<PullRequest>> getPullRequests(@Path("user") String userName, @Path("repo") String repoName);

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String username);

}
