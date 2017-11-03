package infnet.edu.br.desafioandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import infnet.edu.br.desafioandroid.API.ApiClient;
import infnet.edu.br.desafioandroid.API.GitHubApi;
import infnet.edu.br.desafioandroid.R;
import infnet.edu.br.desafioandroid.adapter.RecyclerAdapter;
import infnet.edu.br.desafioandroid.adapter.RecyclerSampleAdapter;
import infnet.edu.br.desafioandroid.model.GitHubCatalog;
import infnet.edu.br.desafioandroid.model.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Repository> repositories;
    private RecyclerAdapter recyclerRepoAdapter;
    private RecyclerView recyclerView;

    private GitHubApi gitHubApi;
    private static final String TAG = "joao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new RecyclerSampleAdapter());

        repositories = new ArrayList<>();

        gitHubApi = ApiClient.getGitHubApi().create(GitHubApi.class);
        Call<GitHubCatalog> catalogCall = gitHubApi.getCatalog();

        catalogCall.enqueue(new Callback<GitHubCatalog>() {
            @Override
            public void onResponse(Call<GitHubCatalog> call, Response<GitHubCatalog> response) {
                if (response.isSuccessful()) {

                    GitHubCatalog gitHubCatalog = response.body();

                    repositories = (ArrayList<Repository>) gitHubCatalog.getRepositories();

                    recyclerRepoAdapter = new RecyclerAdapter(repositories, getApplicationContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(recyclerRepoAdapter);
                    recyclerRepoAdapter.notifyDataSetChanged();

                } else {
                    Log.i(TAG , "retornou com erro" + response.toString());
                } // End else
            } // End onResponse

            @Override
            public void onFailure(Call<GitHubCatalog> call, Throwable t) {
                Log.i(TAG , "failure: " + t.getMessage());
            }
        }); // End catalogCall.enqueue

    } // End onCreate
}
