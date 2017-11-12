package com.nexaas.com.desafio_mobile_android.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nexaas.com.desafio_mobile_android.R;
import com.nexaas.com.desafio_mobile_android.adapter.PullRequestAdapter;
import com.nexaas.com.desafio_mobile_android.adapter.RepositoryAdapter;
import com.nexaas.com.desafio_mobile_android.client.PullRequentClient;
import com.nexaas.com.desafio_mobile_android.model.PullRequestEntity;
import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.nexaas.com.desafio_mobile_android.viewModel.PullRequestViewModel;

import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView pullRequestRecyclerView;
    private PullRequestAdapter pullRequestAdapter;
    private PullRequestViewModel pullRequestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        this.initComponents();
        this.pullRequestViewModel = new PullRequestViewModel();
        String nameRepository =  getIntent().getExtras().getString("nameRepository");
        setTitle(nameRepository);
        String urlPullRequest = getIntent().getExtras().getString("urlPullRequest");
        this.getListPullRequest(urlPullRequest);
    }

    private void initComponents(){
        this.pullRequestRecyclerView = findViewById(R.id.pullRequestRecyclerView);
    }

    @SuppressLint("StaticFieldLeak")
    private void getListPullRequest(String url){
        new AsyncTask<String, Void, List<PullRequestEntity>>() {

            private ProgressDialog progressBar;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = new ProgressDialog(PullRequestActivity.this);
                progressBar.setMessage("Carregando");
                progressBar.show();
            }

            @Override
            protected List<PullRequestEntity> doInBackground(String ...params) {
                return pullRequestViewModel.getListRepository(params[0]);

            }

            @Override
            protected void onPostExecute(List<PullRequestEntity> list) {
                super.onPostExecute(list);
                loadListPullRequest(list);
                progressBar.dismiss();

            }
        }.execute(url);

    }

    private void loadListPullRequest(List<PullRequestEntity> list){
        if(this.pullRequestAdapter == null){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            this.pullRequestRecyclerView.setLayoutManager(layoutManager);
            this.pullRequestAdapter = new PullRequestAdapter(list, this) ;
//            this.pullRequestAdapter.setOnItemClickListener(new RepositoryAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(RepositoryEntity item) {
//                    Intent intent = new Intent(RepositoryActivity.this, PullRequestActivity.class);
//
//                    startActivity(intent);
//                }
//            });
            this.pullRequestRecyclerView.setAdapter(this.pullRequestAdapter);
        }
    }

}
