package com.nexaas.com.desafio_mobile_android.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nexaas.com.desafio_mobile_android.R;
import com.nexaas.com.desafio_mobile_android.adapter.RepositoryAdapter;
import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.nexaas.com.desafio_mobile_android.viewModel.RepositoryVewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by marcos_viana on 11/11/17.
 */

public class RepositoryActivity extends AppCompatActivity {

    private RecyclerView repositoryRecyclerView;
    private RepositoryAdapter repositoryAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<RepositoryEntity> list;
    private int page = 1;
    private boolean carregou = false;

    private RepositoryVewModel repositoryVewModel = new RepositoryVewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        setTitle("GitHub JavaPop");
        this.initComponents();
        this.getRepository(this.page);

    }

    @SuppressLint("StaticFieldLeak")
    private void getRepository(int page) {
        new AsyncTask<Integer, Void, List<RepositoryEntity>>() {

            private ProgressDialog progressBar;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = new ProgressDialog(RepositoryActivity.this);
                progressBar.setMessage("Carregando");
                progressBar.show();
            }

            @Override
            protected List<RepositoryEntity> doInBackground(Integer ...params) {
                return repositoryVewModel.getListRepository(params[0]);
            }

            @Override
            protected void onPostExecute(List<RepositoryEntity> repositoryEntities) {
                super.onPostExecute(repositoryEntities);
                loadListRepository(new ArrayList(repositoryEntities));
                progressBar.dismiss();

            }
        }.execute(page);
    }

    private void loadListRepository(ArrayList<RepositoryEntity> list){

        if(this.repositoryAdapter == null){
           // this.list = list;
            this.layoutManager = new LinearLayoutManager(this);

            this.repositoryRecyclerView.setLayoutManager(layoutManager);
            this.repositoryAdapter = new RepositoryAdapter(new ArrayList(list), this) ;
            this.repositoryAdapter.setOnItemClickListener(new RepositoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RepositoryEntity item) {
                    Intent intent = new Intent(RepositoryActivity.this, PullRequestActivity.class);
                    intent.putExtra("nameRepository", item.getName());
                    intent.putExtra("urlPullRequest", item.getFull_name());
                    startActivity(intent);
                }
            });
            this.repositoryRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(layoutManager.findLastCompletelyVisibleItemPosition() == repositoryAdapter.getItemCount() - 1){
                        carregou = true;
                        repositoryRecyclerView.scrollToPosition(layoutManager.findLastVisibleItemPosition() );
                        page ++;
                        getRepository(page);
                   }
                }

            });

            this.repositoryRecyclerView.setAdapter(this.repositoryAdapter);
        }else{
            repositoryAdapter.addAll(list);

        }
    }

    private void initComponents(){
        this.repositoryRecyclerView = findViewById(R.id.repositoryRecyclerView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
