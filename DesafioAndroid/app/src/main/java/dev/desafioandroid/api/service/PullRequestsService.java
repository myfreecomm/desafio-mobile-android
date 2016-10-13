package dev.desafioandroid.api.service;

import java.util.List;

import dev.desafioandroid.api.pojo.PullRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PullRequestsService {

    @GET("repos/{owner}/{repo}/pulls?state=all")
    Call<List<PullRequest>> getPullRequests(@Path("owner") String owner, @Path("repo") String repo);
}