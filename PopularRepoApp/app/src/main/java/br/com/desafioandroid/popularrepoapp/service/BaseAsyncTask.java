package br.com.desafioandroid.popularrepoapp.service;

import android.os.AsyncTask;

import br.com.desafioandroid.popularrepoapp.BuildConfig;
import br.com.desafioandroid.popularrepoapp.interfaces.api.ApiServices;
import br.com.desafioandroid.popularrepoapp.interfaces.delegator.AsyncTaskDelegate;
import br.com.desafioandroid.popularrepoapp.net.RetrofitConnection;

/**
 * Created by Dennys on 15/11/2017.
 */

public abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected AsyncTaskDelegate asyncTaskDelegate;
    public BaseAsyncTask(){}

    protected ApiServices getApiServices() {

        return new RetrofitConnection().connection(BuildConfig.API_URL).create(ApiServices.class);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (asyncTaskDelegate != null) {
            asyncTaskDelegate.processStart();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        asyncTaskDelegate.processFinish(null);
    }
}
