package br.com.nexas.appgithubjava.retrofit;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.nexas.appgithubjava.service.RepositorioService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by user on 02/11/2017.
 */

public class RepositorioRetrofit {

    private static Retrofit retrofit = null;
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder client = new OkHttpClient.Builder();

    public RepositorioRetrofit() {

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(interceptor);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
                    .build();
            Log.i("RETROFIT", "Acessou Retrofit ");
        }
        return retrofit;
    }


}
