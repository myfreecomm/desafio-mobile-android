package br.com.desafioandroid.popularrepoapp.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.R;
import br.com.desafioandroid.popularrepoapp.entity.ItemRepositoryEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.mvp.MVP;
import br.com.desafioandroid.popularrepoapp.presenter.HomePresenterImpl;
import br.com.desafioandroid.popularrepoapp.view.adapter.RepositoryRecycleViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MVP.HomeView, Runnable {

    private final int WRITE_REQUEST_CODE = 100;
    @BindView(R.id.toolbar) public Toolbar toolbar;
    @BindView(R.id.recycler_view) public RecyclerView recyclerView;
    @BindView(R.id.progress_bar) public ProgressBar progressBar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    public HomePresenterImpl presenterHome;
    private LinearLayoutManager linearLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.activity_home_title));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);

        presenterHome = new HomePresenterImpl(this);
        presenterHome.findRepositoryByPage(presenterHome.page++);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

            // show dialog for user
            requestPermissions(permissions, WRITE_REQUEST_CODE);
        } else {
            createOrUpdateDatabase();
        }
    }

    private void createOrUpdateDatabase() {
        Handler h = new Handler();
        h.postDelayed(this, 800);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case WRITE_REQUEST_CODE:

                createOrUpdateDatabase();
                break;
        }
    }



    @Override
    public void run() {

      presenterHome.createOrUpdateDatabase();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public List<ItemRepositoryEntity> getItens() {
        return ((RepositoryRecycleViewAdapter) recyclerView.getAdapter()).getItems();
    }

    @Override
    public void loadRecycleViewRepository() {

            recyclerView.setHasFixedSize(true);
            linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new RepositoryRecycleViewAdapter(presenterHome.listItenItemRepositoryEntities));
            recyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(HomePresenterImpl.MAX_ITEMS_PER_REQUEST, linearLayoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {

                if (progressBar.getVisibility() != View.VISIBLE) {
                    if (presenterHome.loadMoreItens()) {
                        refreshView(recyclerView, new RepositoryRecycleViewAdapter(presenterHome.listItenItemRepositoryEntities), firstVisibleItemPosition);
                    }
                }
            }
        };
    }

}
