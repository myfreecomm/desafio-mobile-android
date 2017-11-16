package br.com.desafioandroid.popularrepoapp.net;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConnection {

    private static String TAG = "PopularRepoApp";

    public RetrofitConnection() {

    }

    public Retrofit connection(String url){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {

                                Request requestBuilder = chain.request().newBuilder()
                                        .addHeader("Accept", "application/json")
                                        .addHeader("Content-Type", "application/json").build();

                                        Response reponse = chain.proceed(requestBuilder);

                                        Log.i(TAG, "final request url: " + reponse.request().url());
                                        Log.i(TAG, "message response" + reponse.message());
                                        Log.i(TAG, "Response Code: " + reponse.code());


                                return reponse;
                            }
                        }).build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;

    }
}
