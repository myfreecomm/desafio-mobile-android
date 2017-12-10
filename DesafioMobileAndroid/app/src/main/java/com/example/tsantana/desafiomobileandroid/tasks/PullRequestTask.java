package com.example.tsantana.desafiomobileandroid.tasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import com.example.tsantana.desafiomobileandroid.data.dao.PullRequestDao;
import com.example.tsantana.desafiomobileandroid.data.model.PullRequest;

/**
 * Created by tsantana on 08/12/2017.
 */

public class PullRequestTask extends AsyncTask< String,Void,ArrayList<PullRequest>> {

    private PullRequestDao pullRequestDao;
    private ArrayList<PullRequest> mPullRequestList;

    @Override
    protected ArrayList<PullRequest> doInBackground(String... strings) {

        pullRequestDao = new PullRequestDao();
        mPullRequestList = pullRequestDao.getAllPullRequestsByRepositoryNameOwnerName(strings[0],strings[1]);
        return mPullRequestList;
    }

    @Override
    protected void onPostExecute(ArrayList<PullRequest> pullRequests) {
        super.onPostExecute(pullRequests);
    }
}
