package myfreecomm.br.com.desafio_mobile_android.Presenter;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import myfreecomm.br.com.desafio_mobile_android.Model.Repositories;
import myfreecomm.br.com.desafio_mobile_android.Service.APIService;
import myfreecomm.br.com.desafio_mobile_android.ViewFragment.HomeContentFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeContentPresenter {

    private static String BASE_URL = "https://api.github.com";

    private HomeContentFragment homeContentFragment;
    private Repositories repositories;

    public HomeContentPresenter(HomeContentFragment homeContentFragment) {
        this.homeContentFragment = homeContentFragment;
    }

    public void loadRepositories(int pageNumber){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<Repositories> call = service.loadRepositoriesRetrofit("Java", "stars", pageNumber);
        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                if (response.isSuccessful()) {

                    repositories = response.body();

                    if(repositories != null){


                        homeContentFragment.loadDataItems(repositories.getItems());

                    }else {

                        homeContentFragment.noDataRepository();
                    }

                }
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {

                Log.i("error_code_load", t.getMessage());

            }
        });
    }

    public void updateRepositories(int pageNumber){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<Repositories> call = service.loadRepositoriesRetrofit("Java", "stars", pageNumber);
        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                if (response.isSuccessful()) {

                    repositories = response.body();

                    if(repositories != null){

                        homeContentFragment.updateDataItems(repositories.getItems());

                    }else {

                        homeContentFragment.noDataRepository();
                    }
                }
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {

                Log.i("error_code_load", t.getMessage());

            }
        });

    }

}
