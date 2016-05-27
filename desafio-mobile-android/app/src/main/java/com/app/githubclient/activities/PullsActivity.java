package com.app.githubclient.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.app.githubclient.R;
import com.app.githubclient.adapters.PullsListAdapter;
import com.app.githubclient.extras.DividerItemDecoration;
import com.app.githubclient.extras.SaveSharedPreference;
import com.app.githubclient.models.Pull;
import com.app.githubclient.models.Repository;
import com.app.githubclient.services.CustomCallback;
import com.app.githubclient.services.RestApi;
import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by thaynan on 25/05/2016.
 */
public class PullsActivity extends AppCompatActivity {

    RecyclerView listRepositories;

    public static final String REPOSITORY_KEY = "REPOSITORY_KEY";
    public static final String PULLS_KEY = "PULLS_KEY";
    int currentPage = 1;
    int maxPages;
    boolean loading;
    Repository repository;
    private CustomCallback<List<Pull>> callbackGetPulls;
    private PullsListAdapter pullsListAdapter;
    private ConnectivityManager connectivityManager;
    private NetworkInfo activeNetwork;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    Gson gson;
    Type listOfTestObject = new TypeToken<List<Pull>>(){}.getType();
    private String jsonActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
        listOfTestObject = new TypeToken<List<Pull>>(){}.getType();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                repository = null;
                finish();
            } else {
                repository = (Repository) extras.getSerializable(REPOSITORY_KEY);
            }
        } else {
            repository = (Repository) savedInstanceState.getSerializable(REPOSITORY_KEY);
            String pullsJsonSave = savedInstanceState.getString(PULLS_KEY);
            if(pullsJsonSave != "" || pullsJsonSave != null){
                List<Pull> listAux = gson.fromJson(pullsJsonSave, listOfTestObject);
                currentPage = 1;
                addPullsOnList(listAux);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(repository != null) {
            getSupportActionBar().setTitle(repository.getName());
        }
        listRepositories = (RecyclerView) findViewById(R.id.list_repositories);


        connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();

        pullsListAdapter = new PullsListAdapter(PullsActivity.this);

        //toggle.setDisplayHomeAsUpEnabled(false);

        if (listRepositories.getLayoutManager() == null) {
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            listRepositories.setLayoutManager(manager);
            listRepositories.addItemDecoration(new DividerItemDecoration(PullsActivity.this, LinearLayoutManager.VERTICAL));
            listRepositories.setAdapter(pullsListAdapter);

            listRepositories.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    int visibleItemCount = manager.getChildCount();
                    int totalItemCount = manager.getItemCount();
                    int pastVisibleItems = manager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount - 2) {
                            if (checkConnection()) {
                                currentPage++;
                                requirePulls(currentPage, callbackGetPulls);
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
                 requirePulls(currentPage, callbackGetPulls);
             }
         });
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        callbackGetPulls = new CustomCallback<List<Pull>>() {

            @Override
            public void succefull(final List<Pull> response) {

                addPullsOnList(response);
                jsonActual = gson.toJson(response, listOfTestObject);
            }

            @Override
            public void failure(Throwable t) {
                LoadState(false);
                mSwipeRefreshLayout.setEnabled(false);
                finish();
            }
        };

        if (checkConnection()) {
            mSwipeRefreshLayout.setEnabled(true);
            requirePulls(currentPage, callbackGetPulls);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        //savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        //savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

        savedInstanceState.putSerializable(REPOSITORY_KEY, repository);
        savedInstanceState.putString(PULLS_KEY, jsonActual);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private void requirePulls(int page, CustomCallback<List<Pull>> callback) {
        LoadState(true);
        if(repository != null) {
            RestApi.getInstance().getPulls(repository.getOwner().getLogin(), repository.getName(), page, callback);
        }else{
            finish();
        }
    }

    void LoadState(final boolean stateLoad) {
        loading = stateLoad;
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(stateLoad);
            }
        });
    }

    private void addPullsOnList(final List<Pull> pulls) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if(currentPage <= 1){
                            pullsListAdapter.addNewPulls(pulls);
                        }else {
                            pullsListAdapter.addPulls(pulls);
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
            //connectionError.setVisibility(View.GONE);
        } else {
            //connectionError.setVisibility(View.VISIBLE);
        }
        return isConnected;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
