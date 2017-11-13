package com.innerforge.testapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.innerforge.testapp.repository.detail.PullRequest;
import com.innerforge.testapp.repository.list.Repository;
import com.innerforge.testapp.repository.list.RepositoryListResponse;
import com.innerforge.testapp.requests.DataWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuizH on 08/11/2017.
 */

public class RepositoryViewModel extends ViewModel {

    private MediatorLiveData<DataWrapper<RepositoryListResponse>> mRepositoryListResponse;

    private MediatorLiveData<DataWrapper<List<PullRequest>>> mPullRequests;
    private RepositoryController mIssueRepository;
    private int lastPage;

    // No argument constructor
    public RepositoryViewModel() {
        mRepositoryListResponse = new MediatorLiveData<>();
        mPullRequests = new MediatorLiveData<>();
        mIssueRepository = new RepositoryController();
    }

    @NonNull
    public LiveData<DataWrapper<RepositoryListResponse>> getRepositoryListResponse() {
        return mRepositoryListResponse;
    }

    private List<Repository> repositories = new ArrayList<>();

    public LiveData<DataWrapper<RepositoryListResponse>> loadRepositories(){
        if(mRepositoryListResponse.getValue() == null){
            return loadRepositories(1);
        }
        return mRepositoryListResponse;
    }
    public LiveData<DataWrapper<RepositoryListResponse>> loadRepositories(int page) {

        mRepositoryListResponse.addSource(
                mIssueRepository.getRepositories(page),
                apiResponse -> {

                    if (mRepositoryListResponse.getValue() != null) {
                        if(apiResponse.getCode() == 200) {
                            repositories.addAll(apiResponse.getData().getRepositories());
                            List<Repository> repositoriesAux = new ArrayList<>();
                            repositoriesAux.addAll(repositories);
                            apiResponse.getData().setRepositories(repositoriesAux);
                            lastPage = page;
                            //mRepositoryListResponse.getValue().getData().addRepositories(apiResponse.getData().getRepositories());//..setValue(apiResponse)
                        }
                        else{
                            List<Repository> repositoriesAux = new ArrayList<>();
                            repositoriesAux.addAll(repositories);
                            apiResponse.getData().setRepositories(repositoriesAux);
                        }
                    }
                    else{
                        if(apiResponse.getCode() == 200) {
                            lastPage = page;
                        }
                        repositories.addAll(apiResponse.getData().getRepositories());
                    }
                    mRepositoryListResponse.setValue(apiResponse);
                }
        );
        return mRepositoryListResponse;
    }

    @NonNull
    public LiveData<DataWrapper<List<PullRequest>>> getPullRequests() {
        return mPullRequests;
    }

    public LiveData<DataWrapper<List<PullRequest>>> loadPullRequests(String owner, String repo) {

        mPullRequests.addSource(
                mIssueRepository.getPullRequests(owner, repo),
                apiResponse -> {
                    mPullRequests.setValue(apiResponse);
                }
        );
        return mPullRequests;
    }

    public int getLastPage() {
        return lastPage;
    }
}
