package br.com.arthurcordova.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.arthurcordova.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acstapassoli on 16/10/17.
 */

public abstract class Controller implements IController {

    protected Gson gsonBuilder;
    protected Retrofit retrofit;

    @Override
    public void start() {
        gsonBuilder = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_MAIN)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();
    }
}
