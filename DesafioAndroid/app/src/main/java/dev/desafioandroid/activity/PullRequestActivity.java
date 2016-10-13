package dev.desafioandroid.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.desafioandroid.R;
import dev.desafioandroid.adapter.PullAdapter;
import dev.desafioandroid.api.Api;
import dev.desafioandroid.api.pojo.PullRequest;
import dev.desafioandroid.util.ItemClickListener;
import dev.desafioandroid.util.ResponseListener;
import dev.desafioandroid.util.SimpleDividerItemDecoration;
import dev.desafioandroid.util.Utils;

public class PullRequestActivity extends AppCompatActivity implements ItemClickListener,
        ResponseListener {

    @BindView(R.id.loading) ProgressBar loading;
    @BindView(R.id.pull_list) RecyclerView pullList;

    private Unbinder unbinder;
    private PullAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        unbinder = ButterKnife.bind(this);
        setupToolbar();
        setupList();
        loadPullRequests(getOwner(), getRepo());
    }

    private String getOwner() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString(MainActivity.EXTRA_OWNER);
        }
        return "";
    }

    private String getRepo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString(MainActivity.EXTRA_REPO);
        }
        return "";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(Object item, int position) {
        PullRequest pr = (PullRequest) item;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(pr.getHtmlUrl()));
        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(Object o) {
        pullList.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);

        if (o instanceof List) {
            mAdapter.addAll((List<PullRequest>) o);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        loading.setVisibility(View.GONE);
        Utils.showSimpleDialog(this, errorMessage, null);
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getRepo());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupList() {
        mAdapter = new PullAdapter(this, this);
        pullList.setLayoutManager(new LinearLayoutManager(this));
        pullList.setItemAnimator(new DefaultItemAnimator());
        pullList.addItemDecoration(new SimpleDividerItemDecoration(this));
        pullList.setAdapter(mAdapter);
    }

    private void loadPullRequests(String owner, String repo) {
        Api.getInstance(this).getPulls(owner, repo, this);
    }
}