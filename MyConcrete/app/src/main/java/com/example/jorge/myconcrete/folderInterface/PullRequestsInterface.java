package com.example.jorge.myconcrete.folderInterface;

import com.example.jorge.myconcrete.model.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jorge on 09/11/2017.
 */

public interface PullRequestsInterface {

    @GET("/repos/{name}/{login}/pulls")
    Call<List<PullRequest>> getPullRequests(@Path("name") String user,@Path("login") String login);

}
