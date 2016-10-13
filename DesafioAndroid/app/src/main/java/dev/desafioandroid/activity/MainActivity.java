package dev.desafioandroid.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.paginate.Paginate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.desafioandroid.R;
import dev.desafioandroid.adapter.RepoAdapter;
import dev.desafioandroid.api.Api;
import dev.desafioandroid.api.pojo.GitHubInfo;
import dev.desafioandroid.api.pojo.Repo;
import dev.desafioandroid.util.CustomLoadingListItemCreator;
import dev.desafioandroid.util.ItemClickListener;
import dev.desafioandroid.util.ResponseListener;
import dev.desafioandroid.util.SimpleDividerItemDecoration;
import dev.desafioandroid.util.Utils;

public class MainActivity extends AppCompatActivity implements ResponseListener, ItemClickListener {

    @BindView(R.id.repo_list) RecyclerView repoList;
    @BindView(R.id.loading) ProgressBar loading;

    private Unbinder unbinder;
    private RepoAdapter mAdapter;
    private long totalPages = -1;
    private boolean loadingInProgress;
    private int currentPageNumber = 1;

    private static final short PERMISSIONS_REQUEST_CUSTOM = 665;
    public static final String EXTRA_OWNER = "__owner__";
    public static final String EXTRA_REPO = "__repo__";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setupToolbar();
        requestPermissions();
    }

    @Override
    public void onSuccess(Object o) {
        repoList.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);

        if (o instanceof GitHubInfo) {
            GitHubInfo gitHubInfo = (GitHubInfo) o;

            if (totalPages == -1) {
                if (gitHubInfo.getTotalCount() % 30 == 0)
                    totalPages = gitHubInfo.getTotalCount() / 30;
                else
                    totalPages = (gitHubInfo.getTotalCount() / 30) + 1;
            }

            mAdapter.addAll(gitHubInfo.getItems());
            mAdapter.notifyDataSetChanged();

            loadingInProgress = false;
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        loading.setVisibility(View.GONE);
        Utils.showSimpleDialog(this, errorMessage, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onItemClickListener(Object item, int position) {
        Repo repo = (Repo) item;

        Intent i = new Intent(this, PullRequestActivity.class);
        i.putExtra(EXTRA_OWNER, repo.getOwner().getLogin());
        i.putExtra(EXTRA_REPO, repo.getName());

        startActivity(i, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CUSTOM:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupList();
                } else {
                    Utils.showSimpleDialog(this, getString(R.string.no_internet_permission),
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                }
        }
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, PERMISSIONS_REQUEST_CUSTOM);
        } else {
            setupList();
        }
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setupList() {
        mAdapter = new RepoAdapter(this, this);
        repoList.setLayoutManager(new LinearLayoutManager(this));
        repoList.setItemAnimator(new DefaultItemAnimator());
        repoList.addItemDecoration(new SimpleDividerItemDecoration(this));
        repoList.setAdapter(mAdapter);

        Paginate.with(repoList, callbacks)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(new CustomLoadingListItemCreator())
                .build();
    }

    private void loadRepos(int pageNumber) {
        Api.getInstance(this).getRepos("language:Java", "stars", pageNumber, this);
    }

    Paginate.Callbacks callbacks = new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            loadRepos(currentPageNumber);
            currentPageNumber++;
            loadingInProgress = true;
        }

        @Override
        public boolean isLoading() {
            return loadingInProgress;
        }

        @Override
        public boolean hasLoadedAllItems() {
            return totalPages == currentPageNumber;
        }
    };
}