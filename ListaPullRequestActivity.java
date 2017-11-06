package br.com.nexas.appgithubjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.nexas.appgithubjava.adapter.PullRequestsAdapter;
import br.com.nexas.appgithubjava.modelo.PullRequest;
import br.com.nexas.appgithubjava.retrofit.ApiUtils;
import br.com.nexas.appgithubjava.service.RepositorioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPullRequestActivity extends AppCompatActivity {

    private String authorName;
    private String repoName;
    private RecyclerView listPullRequest;
    private PullRequestsAdapter adapter;
    private RepositorioService service;
    private List<PullRequest> pullRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pull_request);

        authorName = getIntent().getStringExtra("authorName");
        repoName   = getIntent().getStringExtra("repoName");

        if (authorName.isEmpty() || repoName.isEmpty()) {
            Toast.makeText(this, "Erro no aplicativo ... ", Toast.LENGTH_SHORT).show();
            finish();
        }

        service = ApiUtils.getGitHubService();
        listPullRequest = (RecyclerView) findViewById(R.id.lista_Pull_Requests);
        adapter = new PullRequestsAdapter(this, new ArrayList<PullRequest>(0),
                  new PullRequestsAdapter.PullRequestListener() {
            @Override
            public void onPullRequestClick(String url) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listPullRequest.setLayoutManager(layoutManager);
        listPullRequest.setAdapter(adapter);
        listPullRequest.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listPullRequest.addItemDecoration(itemDecoration);
        if (savedInstanceState != null) {
            pullRequests = (List<PullRequest>) savedInstanceState.getSerializable("list");
            adapter.atualizaPullRequest(pullRequests);
        } else {
            carregaPullRequests();
        }

    }

    private void carregaPullRequests() {
           service.getPullRequests(authorName, repoName).enqueue(new Callback<List<PullRequest>>() {
           @Override
           public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                  if (response.isSuccessful()) {
                      pullRequests = response.body();
                      adapter.atualizaPullRequest(pullRequests);
                      Log.d("PullRequestActivity", "Pull requests loaded from GitHub API.");
                  } else {
                     // TODO: handle request errors
                  }
           }

           @Override
           public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                  Toast.makeText(getApplicationContext(), "Erro no aplicativo ...", Toast.LENGTH_SHORT).show();
                  t.printStackTrace();
                  Log.e("PullRequestActivity", "Error ao carregar API.");
           }
    });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          onBackPressed();
          return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
          super.onSaveInstanceState(outState);
          outState.putSerializable("list", (Serializable) pullRequests);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_voltar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
