package br.com.desafioandroid.popularrepoapp.view.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.R;
import br.com.desafioandroid.popularrepoapp.entity.ItemRepositoryEntity;
import br.com.desafioandroid.popularrepoapp.entity.PullRequestEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.mvp.MVP;
import br.com.desafioandroid.popularrepoapp.presenter.PullRequestRepoPresenterImpl;
import br.com.desafioandroid.popularrepoapp.view.adapter.PullRequestRepoRecycleViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.appcompat.R.id.home;

/**
 * Created by Dennys on 15/11/2017.
 */

public class PullRequestReposAcitivity extends BaseActivity implements MVP.PullRequestRepoView {

    public final static String REPOSITORY_ENTITY = "repositoryEntity";
    public PullRequestRepoPresenterImpl pullRequestRepoPresenter;


    @BindView(R.id.toolbar) public Toolbar toolbar;
    @BindView(R.id.recycler_view) public RecyclerView recyclerView;
    @BindView(R.id.progress_bar) public ProgressBar progressBar;
    @BindView(R.id.txt_opened_closed)
    TextView txtOpenedClosed;
    @BindView(R.id.linear_layout_open_close)
    LinearLayout linearLayoutOpenedClosed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pull_request_repo_activity);
        ButterKnife.bind(this);
        ItemRepositoryEntity itemRepositoryEntity = getIntent().getParcelableExtra(REPOSITORY_ENTITY);

        if(itemRepositoryEntity != null) {

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(itemRepositoryEntity.name);
            pullRequestRepoPresenter = new PullRequestRepoPresenterImpl(this);
            pullRequestRepoPresenter.findPullResquestRepoByCreatorRepo(itemRepositoryEntity.owner.login, itemRepositoryEntity.name);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case home:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
    public void loadViewsPullRequestRepo(List<PullRequestEntity> listPullRequestRepo, int opened, int closed) {

        loadValuesOpenedClosed(opened, closed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PullRequestRepoRecycleViewAdapter(listPullRequestRepo));
    }

    private void loadValuesOpenedClosed(int opened, int closed){

        String text = getString(R.string.activity_pull_request_repo_info_open_close, String.valueOf(opened), String.valueOf(closed));
        Spannable spannable = new SpannableString(text);

        int index = text.indexOf("/");

        while (index >= 0) {
            spannable.setSpan(new ForegroundColorSpan(Color.BLACK), index, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            index = text.indexOf("/", index + text.length());

        }
        txtOpenedClosed.setText(spannable);
        linearLayoutOpenedClosed.setVisibility(View.VISIBLE);
    }
}
