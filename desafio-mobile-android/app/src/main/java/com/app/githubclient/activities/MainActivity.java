package com.app.githubclient.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.app.githubclient.R;
import com.app.githubclient.adapters.RepoListAdapter;
import com.app.githubclient.extras.DividerItemDecoration;
import com.app.githubclient.extras.SaveSharedPreference;
import com.app.githubclient.models.Repository;
import com.app.githubclient.models.RepositoryListResponse;
import com.app.githubclient.services.CustomCallback;
import com.app.githubclient.services.RestApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView listRepositories;

    int currentPage = 1;
    int maxPages;
    boolean loading;
    private CustomCallback<RepositoryListResponse> callbackGetRepos;
    private RepoListAdapter repoListAdapter;
    private ConnectivityManager connectivityManager;
    private NetworkInfo activeNetwork;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout connection;
    Gson gson;
    Type listOfTestObject = new TypeToken<List<Repository>>(){}.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
        listOfTestObject = new TypeToken<List<Repository>>(){}.getType();

        connection = (LinearLayout) findViewById(R.id.connection);
        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkConnection()) {
                    connection.setVisibility(View.GONE);
                    currentPage = 1;
                    mSwipeRefreshLayout.setEnabled(true);
                    requireRepositories(currentPage, callbackGetRepos);
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        listRepositories = (RecyclerView)findViewById( R.id.list_repositories );

        connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();


        callbackGetRepos = new CustomCallback<RepositoryListResponse>() {

            @Override
            public void succefull(final RepositoryListResponse response) {
                connection.setVisibility(View.GONE);
                addRepositoriesOnList(response.getRepositoryList());
                new Thread() {
                    public void run() {
                        String reposJsonSave = SaveSharedPreference.getResponse(MainActivity.this);
                        if(reposJsonSave == "" || reposJsonSave == null || currentPage <= 1){
                            SaveSharedPreference.setResponse(MainActivity.this, gson.toJson(response.getRepositoryList(), listOfTestObject));
                        }else {
                            List<Repository> listAux = gson.fromJson(reposJsonSave, listOfTestObject);
                            listAux.addAll(response.getRepositoryList());
                            SaveSharedPreference.setResponse(MainActivity.this, gson.toJson(listAux, listOfTestObject));
                        }
                    }
                }.start();
            }

            @Override
            public void failure(Throwable t) {
                connection.setVisibility(View.VISIBLE);
                LoadState(false);
                mSwipeRefreshLayout.setEnabled(false);
                String reposJsonSave = SaveSharedPreference.getResponse(MainActivity.this);
                if(reposJsonSave != "" || reposJsonSave != null || currentPage <= 1){
                    List<Repository> listAux = gson.fromJson(reposJsonSave, listOfTestObject);
                    addRepositoriesOnList(listAux);
                }
            }
        };

        repoListAdapter = new RepoListAdapter(MainActivity.this);

        if (listRepositories.getLayoutManager() == null) {
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            listRepositories.setLayoutManager(manager);
            listRepositories.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
            listRepositories.setAdapter(repoListAdapter);

            listRepositories.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    int visibleItemCount = manager.getChildCount();
                    int totalItemCount = manager.getItemCount();
                    int pastVisibleItems = manager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount-2) {
                            if (checkConnection()) {
                                currentPage++;
                                requireRepositories(currentPage, callbackGetRepos);
                            }
                        }
                    }
                }
            });
        }


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                currentPage = 1;
                requireRepositories(currentPage, callbackGetRepos);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        String reposJsonSave = SaveSharedPreference.getResponse(MainActivity.this);
        if(reposJsonSave != "" || reposJsonSave != null){
            List<Repository> listAux = gson.fromJson(reposJsonSave, listOfTestObject);
            addRepositoriesOnList(listAux);
        }

        if (checkConnection()) {
            mSwipeRefreshLayout.setEnabled(true);
            requireRepositories(currentPage, callbackGetRepos);
        }

    }

    private void requireRepositories(int page, CustomCallback<RepositoryListResponse> callback) {
        LoadState(true);
        RestApi.getInstance().getRepositories("language:Java", "stars", page, callback);
    }

    void LoadState(final boolean stateLoad){
        loading = stateLoad;
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(stateLoad);
            }
        });
    }

    private void addRepositoriesOnList(final List<Repository> repositories){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if(currentPage <= 1){
                            repoListAdapter.newRepos(repositories);
                        }else {
                            repoListAdapter.addRepos(repositories);
                        }
                        LoadState(false);
                    }
                });
            }
        }.start();
    }

    public boolean checkConnection() {
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting() && activeNetwork.isAvailable();

        if (isConnected) {
            connection.setVisibility(View.GONE);
        } else {
            connection.setVisibility(View.VISIBLE);
            String reposJsonSave = SaveSharedPreference.getResponse(MainActivity.this);
            if(reposJsonSave != "" || reposJsonSave != null || currentPage <= 1){
                List<Repository> listAux = gson.fromJson(reposJsonSave, listOfTestObject);
                addRepositoriesOnList(listAux);
            }
        }
        return isConnected;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
