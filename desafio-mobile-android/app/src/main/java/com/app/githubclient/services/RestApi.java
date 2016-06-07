package com.app.githubclient.services;

import android.app.ProgressDialog;

/**
 * Created by thaynan on 18/05/2016.
 */
public class RestApi extends RestApiAdapter {

    private static RestApi rest;
    ProgressDialog progressDialog;

    private RestApi() {
    }

    public static RestApi getInstance() {
        if(rest == null){
            rest = new RestApi();
        }
        return rest;
    }
}
