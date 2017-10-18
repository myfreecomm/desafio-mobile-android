package br.com.arthurcordova;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.arthurcordova.controller.PullsController;
import br.com.arthurcordova.controller.RepositoriesController;
import br.com.arthurcordova.model.Items;
import br.com.arthurcordova.tools.ArthurCordovaDialog;

public class DetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String EXTRA_MODEL = "model";

    private PullsController mPullsController;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Items mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mItem = (Items) getIntent().getSerializableExtra(EXTRA_MODEL);

        getSupportActionBar().setTitle(mItem.getName());

        mSwipeRefreshLayout = findViewById(R.id.sr_pulls);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = findViewById(R.id.rv_pulls);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPullsController = new PullsController(this, new ArthurCordovaDialog(), mRecyclerView);
        mPullsController.start();
        mPullsController.getGithubPulls(mItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        mPullsController.getGithubPulls(mItem);
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
