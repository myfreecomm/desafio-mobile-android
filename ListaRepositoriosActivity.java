package br.com.nexas.appgithubjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.nexas.appgithubjava.adapter.RepositorioAdapter;
import br.com.nexas.appgithubjava.dao.RepositorioDAO;
import br.com.nexas.appgithubjava.dto.RepositorioDTO;
import br.com.nexas.appgithubjava.modelo.Repositorio;
import br.com.nexas.appgithubjava.retrofit.ApiUtils;
import br.com.nexas.appgithubjava.retrofit.RepositorioRetrofit;
import br.com.nexas.appgithubjava.service.RepositorioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaRepositoriosActivity extends AppCompatActivity {


    private RecyclerView listaRepositorios;
    private int count = 1;
    private boolean carregar = false;
    private RepositorioAdapter adapter;
    private RepositorioService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_repositorios);

        service = ApiUtils.getGitHubService();
        listaRepositorios = (RecyclerView) findViewById(R.id.lista_repositorios);

        adapter = new RepositorioAdapter(this, new ArrayList<Repositorio>(0),
                       new RepositorioAdapter.RepositorioListener() {
        @Override
            public void onRepositoryClick(String authorName, String repoName) {
                   Intent in = new Intent(getApplicationContext(), ListaPullRequestActivity.class);
                   in.putExtra("authorName", authorName);
                   in.putExtra("repoName", repoName);
                   startActivity(in);
            }
        });

        final LinearLayoutManager lm = new LinearLayoutManager(this);
        listaRepositorios.setLayoutManager(lm);
        listaRepositorios.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listaRepositorios.addItemDecoration(itemDecoration);

        if (savedInstanceState != null){
            List<Repositorio> repositorios = (List<Repositorio>) savedInstanceState.getSerializable("repositorios");
            if(repositorios != null){
                adapter.atualizaRepositorios(repositorios);
                this.count = savedInstanceState.getInt("count");
            }

        }

        listaRepositorios.addOnScrollListener(new RecyclerView.OnScrollListener(){
           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
                carregar = true;
                int pastVisibleItems, visibleItemsCount, totalItemCount;
                if (dy > 0) {
                    visibleItemsCount = lm.getChildCount();
                    pastVisibleItems = lm.findFirstVisibleItemPosition();
                    totalItemCount = lm.getItemCount();
                    if (carregar) {
                        if ((visibleItemsCount + pastVisibleItems) >= totalItemCount) {
                            carregar = false;
                            carregaLista(++count);
                        }
                    }
                }
           }
        });

        listaRepositorios.setAdapter(adapter);

    }

    private void carregaLista(int count) {
        service.getRepositorio(count).enqueue(new Callback<RepositorioDTO>() {
            @Override
            public void onResponse(Call<RepositorioDTO> call, Response<RepositorioDTO> response) {
                if (response.isSuccessful()){
                    adapter.addAll(response.body().getRepositorios());
                    Log.i("onResponse chamado", "onResponse chamado com sucesso ");
                }
            }

            @Override
            public void onFailure(Call<RepositorioDTO> call, Throwable t) {
                Log.e("onFailure chamado: ", t.getMessage());
                RepositorioDAO dao = new RepositorioDAO(ListaRepositoriosActivity.this);
                adapter.addAll(dao.selecionaTodosOsRepositorios());
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
//        List<Repositorio> repositorios = (List<Repositorio>) savedInstanceStates.getSerializable("repositorios");
//        adapter.atualizaRepositorios(repositorios);
//        this.count = savedInstanceStates.getInt("count");
        carregaLista(count);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
         super.onSaveInstanceState(outState);
         outState.putSerializable("repositories", (Serializable) adapter.getRepositories());
        outState.putInt("currentPage", count);
    }
}
