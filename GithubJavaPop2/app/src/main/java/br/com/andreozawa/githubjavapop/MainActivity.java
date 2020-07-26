package br.com.andreozawa.githubjavapop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.andreozawa.githubjavapop.activities.PullRequetsActivity;
import br.com.andreozawa.githubjavapop.adapters.PublicReposAdapter;
import br.com.andreozawa.githubjavapop.asynctasks.PublicReposAsyncTask;
import br.com.andreozawa.githubjavapop.model.PublicRepos;
import br.com.andreozawa.githubjavapop.services.PublicReposService;

public class MainActivity extends AppCompatActivity {

    public static final String PUBLIC_REPO_KEY_NAME = "PUBLIC_REPO_KEY_NAME";

    private PublicReposService publicReposService;
    private RecyclerView publicReposRv;
    private PublicReposAdapter publicReposAdapter;
    private List<PublicRepos> publicReposes;
    private ProgressBar publicRepoPb;

    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initComponents();

        this.setupComponents();
    }

    private void initComponents() {
        this.publicReposService = new PublicReposService(this);
        this.publicReposRv = (RecyclerView) findViewById(R.id.public_repos_rv);
        this.publicReposRv.setLayoutManager(new LinearLayoutManager(this));
        this.publicRepoPb = (ProgressBar) findViewById(R.id.public_repo_pb);

        this.publicReposes = new ArrayList<PublicRepos>();
    }

    private void setupComponents() {
        this.setupToolbar();

        this.loadPublicRepos(++page);

        this.publicReposAdapter = new PublicReposAdapter(this.publicReposes, this, this.onItemClick());

        this.publicReposRv.setAdapter(this.publicReposAdapter);

        this.setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.publicReposRv.getLayoutManager();

        this.publicReposRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPodition = linearLayoutManager.findLastVisibleItemPosition();

                if (publicReposAdapter.getItemCount() - 1 == lastVisibleItemPodition) {
                    loadPublicRepos(++page);
                }
            }
        });
    }

    private void loadPublicRepos(int page) {
        this.isShowProgressBar(true);

        this.publicReposService.get(page, new PublicReposAsyncTask.OnPublicReposListener() {
            @Override
            public void onPublicReposCallback(String resultJson) {
                try {
                    JSONObject jsonObject = new JSONObject(resultJson);

                    Type listType = new TypeToken<ArrayList<PublicRepos>>() {
                    }.getType();

                    List<PublicRepos> publicRepos = new Gson().fromJson(String.valueOf(jsonObject.getJSONArray("items")), listType);

                    publicReposAdapter.addPublicReposes(publicRepos);

                    publicReposService.saveAll(publicRepos);

                    isShowProgressBar(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private PublicReposAdapter.OnPublicRepoItemClick onItemClick() {
        return new PublicReposAdapter.OnPublicRepoItemClick() {
            @Override
            public void onClick(PublicRepos publicRepos) {
                Intent intent = new Intent(getContext(), PullRequetsActivity.class);

                intent.putExtra(PUBLIC_REPO_KEY_NAME, publicRepos);

                startActivity(intent);
            }
        };
    }

    private Context getContext() {
        return this;
    }

    private void isShowProgressBar(boolean isShow) {
        this.publicRepoPb.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}
