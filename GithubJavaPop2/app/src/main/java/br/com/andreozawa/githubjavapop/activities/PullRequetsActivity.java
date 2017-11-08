package br.com.andreozawa.githubjavapop.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.andreozawa.githubjavapop.MainActivity;
import br.com.andreozawa.githubjavapop.R;
import br.com.andreozawa.githubjavapop.adapters.PullRequestsAdapter;
import br.com.andreozawa.githubjavapop.asynctasks.PublicReposAsyncTask;
import br.com.andreozawa.githubjavapop.model.PublicRepos;
import br.com.andreozawa.githubjavapop.model.PullRequest;
import br.com.andreozawa.githubjavapop.services.PublicReposService;

public class PullRequetsActivity extends AppCompatActivity {

    private PublicReposService publicReposService;
    private PublicRepos publicRepos;

    private TextView openedTv;
    private TextView closedTv;
    private RecyclerView pullRequestsRv;
    private PullRequestsAdapter pullRequestsAdapter;
    private List<PullRequest> pullRequests;
    private ProgressBar pullRequestPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requets);

        this.publicRepos = (PublicRepos) getIntent().getSerializableExtra(MainActivity.PUBLIC_REPO_KEY_NAME);

        this.initComponents();

        this.setupComponents();
    }

    private void initComponents() {
        this.publicReposService = new PublicReposService(this);
        this.pullRequests = new ArrayList<PullRequest>();

        this.openedTv = (TextView) findViewById(R.id.opened_tv);
        this.closedTv = (TextView) findViewById(R.id.closed_tv);
        this.pullRequestsRv = (RecyclerView) findViewById(R.id.pull_requests_rv);
        this.pullRequestsRv.setLayoutManager(new LinearLayoutManager(this));

        this.pullRequestsAdapter = new PullRequestsAdapter(this.pullRequests, this, this.onPullRequestClick());

        this.pullRequestPb = (ProgressBar) findViewById(R.id.pull_req_pb);
    }

    private PullRequestsAdapter.OnPullRequestItemClickListener onPullRequestClick() {
        return new PullRequestsAdapter.OnPullRequestItemClickListener() {
            @Override
            public void onClick(PullRequest pullRequest) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.getPullRequestPage()));

                startActivity(intent);
            }
        };
    }

    private void setupComponents() {
        this.setupToolbar();

        this.pullRequestsRv.setAdapter(this.pullRequestsAdapter);

        this.loadPullRequests();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(this.publicRepos.getName());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadPullRequests() {
        this.isShowProgressBar(true);

        this.publicReposService.getPullRequests(this.publicRepos, new PublicReposAsyncTask.OnPublicReposListener() {
            @Override
            public void onPublicReposCallback(String resultJson) {
                Type listType = new TypeToken<ArrayList<PullRequest>>() {
                }.getType();

                pullRequests = new Gson().fromJson(resultJson, listType);

                pullRequestsAdapter.setPullRequests(pullRequests);

                updateCount();

                isShowProgressBar(false);
            }
        });
    }

    private void updateCount() {
        int opened = 0;
        int closed = 0;

        for (PullRequest pullRequest : this.pullRequests) {
            if (pullRequest.getState().equals("open")) {
                opened++;
            } else if (pullRequest.getState().equals("closed")) {
                closed++;
            }
        }

        this.closedTv.setText(String.valueOf(closed) + " closed");
        this.openedTv.setText(String.valueOf(opened) + " opened");
    }

    private void isShowProgressBar(boolean isShow) {
        this.pullRequestPb.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}
