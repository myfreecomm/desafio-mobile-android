package com.example.tsantana.desafiomobileandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import com.example.tsantana.desafiomobileandroid.R;
import com.example.tsantana.desafiomobileandroid.adapters.PullRequestAdapter;
import com.example.tsantana.desafiomobileandroid.data.model.PullRequest;
import com.example.tsantana.desafiomobileandroid.tasks.PullRequestTask;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecycleViewPull;
    private PullRequestAdapter mPullAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String owner="";
        String repoName ="";

        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            owner =extras.getString("ownerRepo");
            repoName = extras.getString("repoName");
        }

        toolbar = (Toolbar) findViewById(R.id.toobarSecond);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle(repoName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setElevation(2);


        new PullRequestTask(){
            @Override
            protected void onPostExecute(ArrayList<PullRequest> pullRequests) {

                mRecycleViewPull = (RecyclerView)findViewById(R.id.list_pull);
                LinearLayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
                mRecycleViewPull.setLayoutManager(layoutManager);
                mPullAdapter = new PullRequestAdapter(pullRequests,SecondActivity.this);
                mRecycleViewPull.setAdapter(mPullAdapter);

            }
        }.execute(owner,repoName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if( item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
