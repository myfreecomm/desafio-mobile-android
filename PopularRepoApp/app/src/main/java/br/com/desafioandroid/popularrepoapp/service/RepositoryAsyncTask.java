package br.com.desafioandroid.popularrepoapp.service;


import java.io.IOException;

import br.com.desafioandroid.popularrepoapp.entity.RepositoryEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.delegator.AsyncTaskDelegate;
import retrofit2.Response;

/**
 * Created by Dennys on 15/11/2017.
 */

public class RepositoryAsyncTask extends BaseAsyncTask<String, Void, RepositoryEntity> {


    public RepositoryAsyncTask(AsyncTaskDelegate asyncTaskDelegate) {
        this.asyncTaskDelegate = asyncTaskDelegate;
    }

    @Override
    protected RepositoryEntity doInBackground(String... strings) {

        Response<RepositoryEntity> response;

        try {
            response = getApiServices().repositories("language:Java","stars", Integer.parseInt(strings[0])).execute();

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
    protected void onPostExecute(RepositoryEntity repositoryEntity) {
        super.onPostExecute(repositoryEntity);
        asyncTaskDelegate.processFinish(repositoryEntity);
    }
}
