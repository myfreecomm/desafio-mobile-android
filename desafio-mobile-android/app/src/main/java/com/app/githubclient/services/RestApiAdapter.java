package com.app.githubclient.services;

import android.util.Log;

import com.app.githubclient.models.Pull;
import com.app.githubclient.models.Repository;
import com.app.githubclient.models.RepositoryListResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thaynan on 18/05/2016.
 */
public abstract class RestApiAdapter {

    public final static String URL = "https://api.github.com";
    public final static String TAG = "Service";
    private Retrofit retrofit;
    private RestApiServices rest;

    protected RestApiAdapter() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rest = retrofit.create(RestApiServices.class);
    }




    public void getRepositories(String query, String sort, int page, final CustomCallback<RepositoryListResponse> callback) {

        final Call<RepositoryListResponse> repoCall = rest.searchRepos(query, sort, page);
        repoCall.enqueue(new Callback<RepositoryListResponse>() {
            @Override
            public void onResponse(Call<RepositoryListResponse> call, Response<RepositoryListResponse> response) {
                RepositoryListResponse repoListResponse = response.body();
                if (repoListResponse != null) {
                    callback.succefull(repoListResponse);
                } else {
                    try {
                        callback.failure(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "R: Error POST: " + response.errorBody() + " body: " + response.body()+ " message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RepositoryListResponse> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    public void getPulls(String login, String name, int page, final CustomCallback<List<Pull>> callback) {

        final Call<List<Pull>> pullCall = rest.getPulls(login, name, page);
        pullCall.enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                List<Pull> pullsResponse = response.body();
                if (pullsResponse != null) {
                    callback.succefull(pullsResponse);
                } else {
                    try {
                        callback.failure(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "R: Error POST: " + response.errorBody() + " body: " + response.body()+ " message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {
                callback.failure(t);
            }
        });
    }


    protected Retrofit getRetrofit() {
        return retrofit;
    }

    protected RestApiServices getRest() {
        return rest;
    }



}
