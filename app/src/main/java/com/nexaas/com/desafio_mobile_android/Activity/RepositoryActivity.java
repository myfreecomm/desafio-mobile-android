package com.nexaas.com.desafio_mobile_android.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nexaas.com.desafio_mobile_android.R;
import com.nexaas.com.desafio_mobile_android.adapter.RepositoryAdapter;
import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.nexaas.com.desafio_mobile_android.viewModel.RepositoryVewModel;

import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class RepositoryActivity extends AppCompatActivity {

    private RecyclerView repositoryListView;
    private RepositoryAdapter repositoryAdapter;

    private RepositoryVewModel repositoryVewModel = new RepositoryVewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        setTitle("GitHub JavaPop");
        this.initComponents();
        this.getRepository(1);

    }

    @SuppressLint("StaticFieldLeak")
    private void getRepository(int page){
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
                loadListRepository(repositoryEntities);
                progressBar.dismiss();

            }
        }.execute(page);


    }

    private void loadListRepository(List<RepositoryEntity> list){
        if(this.repositoryAdapter == null){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            this.repositoryListView.setLayoutManager(layoutManager);
            this.repositoryAdapter = new RepositoryAdapter(list) ;
            this.repositoryListView.setAdapter(this.repositoryAdapter);
        }
    }

    private void initComponents(){
        this.repositoryListView = findViewById(R.id.repositoryRecyclerView);
    }
}
