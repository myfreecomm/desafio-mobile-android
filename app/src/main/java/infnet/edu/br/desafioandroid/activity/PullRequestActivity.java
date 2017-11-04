package infnet.edu.br.desafioandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import infnet.edu.br.desafioandroid.API.ApiClient;
import infnet.edu.br.desafioandroid.API.GitHubApi;
import infnet.edu.br.desafioandroid.R;
import infnet.edu.br.desafioandroid.adapter.RecyclerPullRequestAdapter;
import infnet.edu.br.desafioandroid.adapter.RecyclerSampleAdapter;
import infnet.edu.br.desafioandroid.model.GitHubCatalog;
import infnet.edu.br.desafioandroid.model.PullRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestActivity extends AppCompatActivity {

    private ArrayList<PullRequest> pullRequests;
    private RecyclerPullRequestAdapter recyclerPullRequestAdapter;
    private RecyclerView recyclerView;

    private GitHubApi gitHubApi;
    private static final String TAG = "joao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_pull_request);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new RecyclerSampleAdapter());

        pullRequests = new ArrayList<>();

        gitHubApi = ApiClient.getGitHubApi().create(GitHubApi.class);
        Call<List<GitHubCatalog>> catalogCall = gitHubApi.getPullRequest();

        catalogCall.enqueue(new Callback<List<GitHubCatalog>>() {
            @Override
            public void onResponse(Call<List<GitHubCatalog>> call, Response<List<GitHubCatalog>> response) {
                if (response.isSuccessful()) {

                    GitHubCatalog gitHubCatalog = (GitHubCatalog) response.body();

                    pullRequests = (ArrayList<PullRequest>) gitHubCatalog.getPullRequests();
                    recyclerPullRequestAdapter = new RecyclerPullRequestAdapter(pullRequests, getApplicationContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(recyclerPullRequestAdapter);
                    recyclerPullRequestAdapter.notifyDataSetChanged();

                } else {
                    Log.i(TAG , "retornou com erro" + response.toString());
                } // End else
            } // End onResponse

            @Override
            public void onFailure(Call<List<GitHubCatalog>> call, Throwable t) {
                Log.i(TAG , "failure: " + t.getMessage());
            }
        }); // End catalogCall.enqueue

    } // End onCreate
}