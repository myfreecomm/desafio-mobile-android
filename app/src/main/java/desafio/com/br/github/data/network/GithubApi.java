package desafio.com.br.github.data.network;


import desafio.com.br.github.*;
import retrofit2.*;
import retrofit2.converter.gson.*;

/**
 * Created by rafael on 24/01/18.
 */


public class GithubApi {

    private static GithubService githubservice;


    public static GithubService create()
    {
        if(githubservice == null)
        githubservice =  new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);

        return githubservice;
    }
}