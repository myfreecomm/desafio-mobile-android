package com.innerforge.testapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.innerforge.testapp.repository.detail.PullRequest;
import com.innerforge.testapp.repository.list.RepositoryListResponse;
import com.innerforge.testapp.requests.DataWrapper;
import com.innerforge.testapp.requests.GithubApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LuizH on 31/10/2017.
 */

public class RepositoryController {

        public static final String BASE_URL = "https://api.github.com/";
        private GithubApiService mApiService;

        public RepositoryController() {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
            mApiService = retrofit.create(GithubApiService.class);
        }

        public LiveData<DataWrapper<RepositoryListResponse>> getRepositories(int page) {
            final MutableLiveData<DataWrapper<RepositoryListResponse>> liveData = new MutableLiveData<>();
            Call<RepositoryListResponse> call = mApiService.getRepositories(page);
            Log.d("TEST_APP","getRepositories page = "+page);
            call.enqueue(new Callback<RepositoryListResponse>() {
                @Override
                public void onResponse(Call<RepositoryListResponse> call, Response<RepositoryListResponse> response) {
                    Log.d("TEST_APP","getRepositories size = "+response.body().getRepositories().size());
                    liveData.setValue(new DataWrapper(response.body(), response.message(), response.code()));
                }

                @Override
                public void onFailure(Call<RepositoryListResponse> call, Throwable t) {
                    Log.d("TEST_APP","getRepositories onFailure ");
                    liveData.setValue(new DataWrapper(null, t.getMessage(), DataWrapper.ERROR_CODE_GENERIC));
                }
            });
            return liveData;
        }

    public LiveData<DataWrapper<List<PullRequest>>> getPullRequests(String owner, String repo) {
        final MutableLiveData<DataWrapper<List<PullRequest>>> liveData = new MutableLiveData<>();
        Call<List<PullRequest>> call = mApiService.getPullRequests(owner, repo);
        Log.d("TEST_APP","getPullRequests owner = "+owner+" repo = "+repo);
        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                liveData.setValue(new DataWrapper(response.body(), response.message(), response.code()));
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                liveData.setValue(new DataWrapper(null, t.getMessage(), DataWrapper.ERROR_CODE_GENERIC));
            }
        });
        return liveData;
    }
}
