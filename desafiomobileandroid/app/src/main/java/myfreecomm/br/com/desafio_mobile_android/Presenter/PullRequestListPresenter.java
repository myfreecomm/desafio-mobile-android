package myfreecomm.br.com.desafio_mobile_android.Presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import myfreecomm.br.com.desafio_mobile_android.Model.Pull;
import myfreecomm.br.com.desafio_mobile_android.Service.APIService;
import myfreecomm.br.com.desafio_mobile_android.ViewFragment.PullRequestListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullRequestListPresenter {

    private static String BASE_URL = "https://api.github.com";
    private PullRequestListFragment pullRequestListFragment;
    private List<Pull> pull;

    public PullRequestListPresenter(PullRequestListFragment pullRequestListFragment) {
        this.pullRequestListFragment = pullRequestListFragment;
    }

    public void loadPullRequest(String login, String name) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Pull>> call = service.loadPullRequestRetrofit(login, name);
        call.enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                if (response.isSuccessful()) {

                    pull = response.body();

                    if(pull != null){

                        pullRequestListFragment.loadDataItems(pull);

                    }else {

                        pullRequestListFragment.noDataPull();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {

                Log.i("error_code_load", t.getMessage());

            }
        });

    }
}
