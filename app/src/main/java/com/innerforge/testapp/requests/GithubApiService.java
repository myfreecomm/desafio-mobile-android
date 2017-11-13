package com.innerforge.testapp.requests;

import com.innerforge.testapp.repository.detail.PullRequest;
import com.innerforge.testapp.repository.list.RepositoryListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by LuizH on 08/11/2017.
 */
public interface GithubApiService {

    @GET("/search/repositories?q=language:Java&sort=stars")
    Call<RepositoryListResponse> getRepositories(@Query("page") int page);//@Path("owner") String owner, @Path("repo") String repo);


    @GET("/repos/{owner}/{repo}/pulls")
    Call<List<PullRequest>> getPullRequests(@Path("owner") String owner, @Path("repo") String repo);
}