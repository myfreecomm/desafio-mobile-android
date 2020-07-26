package br.com.desafioandroid.popularrepoapp.service;


import java.io.IOException;
import java.util.List;

import br.com.desafioandroid.popularrepoapp.entity.PullRequestEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.delegator.AsyncTaskDelegate;
import retrofit2.Response;

/**
 * Created by Dennys on 15/11/2017.
 */

public class PullRequestRepoAsyncTask extends BaseAsyncTask<String, Void, List<PullRequestEntity>> {

    private final static String STATE_ALL="all";

    public PullRequestRepoAsyncTask(AsyncTaskDelegate asyncTaskDelegate) {
        this.asyncTaskDelegate = asyncTaskDelegate;
    }

    @Override
    protected List<PullRequestEntity> doInBackground(String... strings) {

        Response<List<PullRequestEntity>> response;

        try {
            response = getApiServices().pullRequestRepos(strings[0], strings[1], STATE_ALL).execute();

            if(response != null && response.isSuccessful()){
                return response.body();
            }else{
                cancel(true);
            }
        } catch (IOException e) {
            cancel(true);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<PullRequestEntity> listPullRequestEntiry) {
        super.onPostExecute(listPullRequestEntiry);
        asyncTaskDelegate.processFinish(listPullRequestEntiry);
    }
}
