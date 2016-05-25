package com.app.githubclient.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.app.githubclient.R;
import com.app.githubclient.adapters.RepoListAdapter;
import com.app.githubclient.extras.DividerItemDecoration;
import com.app.githubclient.models.Repository;
import com.app.githubclient.models.RepositoryListResponse;
import com.app.githubclient.services.CustomCallback;
import com.app.githubclient.services.RestApi;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView listRepositories;

    int currentPage = 1;
    int maxPages;
    boolean loading;
    private CustomCallback<RepositoryListResponse> callbackGetRepos;
    private RepoListAdapter repoListAdapter;
    private ConnectivityManager connectivityManager;
    private NetworkInfo activeNetwork;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listRepositories = (RecyclerView)findViewById( R.id.list_repositories );


        connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        repoListAdapter = new RepoListAdapter(MainActivity.this);

        //toggle.setDisplayHomeAsUpEnabled(false);

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
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            if (checkConnection()) {
                                LoadState(true);
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
                requireRepositories(1, new CustomCallback<RepositoryListResponse>() {
                    @Override
                    public void succefull(final RepositoryListResponse response) {
                        addNewsRepositoriesOnList(response.getRepositoryList());
                    }

                    @Override
                    public void failure(Throwable t) {
                        LoadState(false);
                    }
                });
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        callbackGetRepos = new CustomCallback<RepositoryListResponse>() {

            @Override
            public void succefull(final RepositoryListResponse response) {
                addRepositoriesOnList(response.getRepositoryList());
            }

            @Override
            public void failure(Throwable t) {
                LoadState(false);
                mSwipeRefreshLayout.setEnabled(false);
            }
        };

        if (checkConnection()) {
            mSwipeRefreshLayout.setEnabled(true);
            requireRepositories(currentPage, callbackGetRepos);
        } else {
            //mErrorText.setVisibility(View.VISIBLE);
            //mButtonTryAgain.setVisibility(View.VISIBLE);
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
                        repoListAdapter.addRepos(repositories);
                        LoadState(false);
                    }
                });
            }
        }.start();
    }

    private void addNewsRepositoriesOnList(final List<Repository> repositories){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        repoListAdapter.addNewRepos(repositories);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
