package com.example.tsantana.desafiomobileandroid.data.dao;

import com.example.tsantana.desafiomobileandroid.data.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import com.example.tsantana.desafiomobileandroid.data.model.PullRequest;

/**
 * Created by tsantana on 07/12/2017.
 */

public class PullRequestDao {

    private ArrayList<PullRequest>pullRequestArrayList;

    public ArrayList<PullRequest> getPullRequestArrayList() {
        return pullRequestArrayList;
    }

    public PullRequestDao(){
        pullRequestArrayList = new ArrayList<PullRequest>(){
            @Override
            public PullRequest get(int index) {
                try {
                    return super.get(index);
                }catch (Exception e){
                   return new PullRequest();
                }
            }
        };
    }
    public ArrayList<PullRequest>getAllPullRequestsByRepositoryNameOwnerName(String ownerRepoName, String repoName){
        ApiClient client = new ApiClient("https://api.github.com/repos/"+ownerRepoName+"/"+repoName+"/pulls");
        String response = client.getJson();

        Gson gson = new Gson();
        ArrayList<PullRequest> prs = gson.fromJson(response, new TypeToken<ArrayList<PullRequest>>(){}.getType());

        return (prs == null) ? getPullRequestArrayList(): prs;
    }

}
