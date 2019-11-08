package com.example.tsantana.desafiomobileandroid.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.tsantana.desafiomobileandroid.R;
import com.example.tsantana.desafiomobileandroid.adapters.RepositorioAdapter;

import java.util.ArrayList;

import com.example.tsantana.desafiomobileandroid.data.model.Repositorio;
import com.example.tsantana.desafiomobileandroid.data.model.Search;
import com.example.tsantana.desafiomobileandroid.data.dao.SearchDao;
import com.example.tsantana.desafiomobileandroid.tasks.SearchTask;
import com.example.tsantana.desafiomobileandroid.utils.CommonUtils;

public class MainActivity extends AppCompatActivity implements RepositorioAdapter.OnLoadMoreListener {

    private ArrayList<Repositorio> repositorioArrayList ;
    private RecyclerView mrecyclerView;
    private RepositorioAdapter adapter;
    private Context context;
    private SearchDao searchDao;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toobarMain);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Desafio Mobile Android");


        repositorioArrayList = new ArrayList<>();
        searchDao = new SearchDao();

        mrecyclerView = (RecyclerView) findViewById(R.id.list_repo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutManager);

        adapter = new RepositorioAdapter(this);
        mrecyclerView.setAdapter(adapter);
        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                //só realiza o evento quando chegar aos 4 ultimos itens do adapter
                if (llManager.findLastCompletelyVisibleItemPosition() == (adapter.getItemCount() - 4)) {
                    adapter.showLoading();
                }
            }
        });

        loadPrimeiraPagina();
    }



    private void loadPrimeiraPagina() {
        new SearchTask(){
            @Override
            protected void onPostExecute(Search pesquisa) {
                super.onPostExecute(pesquisa);
                if (CommonUtils.isNetworkAvailable(MainActivity.this)) {

                    repositorioArrayList.clear();
                    adapter.addAll(pesquisa.getRepositorios());
                }
            }
        }.execute(1);
    }

    @Override
    public void onLoadMore(int page) {

        new SearchTask(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                adapter.showLoading();
            }
            @Override
            protected void onPostExecute(Search search) {
                super.onPostExecute(search);
                adapter.dismissLoading();
                adapter.addItemMore(search.getRepositorios());
                adapter.setMore(true);
            }
        }.execute(page);
    }
}
