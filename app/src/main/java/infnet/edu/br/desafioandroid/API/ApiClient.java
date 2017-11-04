package infnet.edu.br.desafioandroid.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class ApiClient {
    public static final String BASE_URL = "https://api.github.com/";
    public static Retrofit retrofit = null;

    public static Retrofit getGitHubApi() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getGitPullRequest() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create())
                    .build();
        }
        return retrofit;
    }
}