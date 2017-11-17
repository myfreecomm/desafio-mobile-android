package com.example.jorge.myconcrete;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.jorge.myconcrete.adapter.PullRequestsAdapter;
import com.example.jorge.myconcrete.adapter.PullRequestsAdapter.PullRequestsAdapterOnClickHandler;
import com.example.jorge.myconcrete.folderInterface.PullRequestsInterface;
import com.example.jorge.myconcrete.model.PullRequest;
import com.example.jorge.myconcrete.utilite.Common;
import com.example.jorge.myconcrete.utilite.Utilite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Class Detail list in RecyclerView all Pull requests .
 */
public class DetailActivity extends AppCompatActivity implements PullRequestsAdapterOnClickHandler {

    PullRequestsAdapter mPullRequestsAdapter;
    private PullRequestsInterface mPullRequestsInterface;

    @BindView(R.id.rv_pull_requests)
    RecyclerView mRecyclerView;


    String mName;
    String mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /**
         * Get putExtra for Activity Main .
         */
        Bundle extras = getIntent().getExtras();

        mName = extras.getString(Utilite.PUT_EXTRA_NAME);
        mLogin = extras.getString(Utilite.PUT_EXTRA_LOGIN);

        /**
         * Put Name Repositorie in  title.
         */
        this.setTitle(mName);

        /**
         * Change fidbyIF for  ButterKnife .
         */
        ButterKnife.bind(this);


        /**
         * use RecyclerView for list the PullRequest .
         */
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
        mPullRequestsAdapter = new PullRequestsAdapter(this);


        /**
         * checks if internet is ok .
         */
        if (Common.isOnline(getSystemService(Context.CONNECTIVITY_SERVICE))) {
            createStackOverflowAPI();
            mPullRequestsInterface.getPullRequests(mLogin, mName).enqueue(pullRequestsCallback);
        }else{
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, R.string.Error_Access,Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private Callback<List<PullRequest>> pullRequestsCallback = new Callback<List<PullRequest>>() {
        @Override
        public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
            try{
                if (response.isSuccessful()) {
                    List<PullRequest> data = new ArrayList<>();
                    data.addAll(response.body());
                    mRecyclerView.setAdapter(new PullRequestsAdapter(data));

                } else {
                    Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
                }
            }catch(NullPointerException e){
                System.out.println("onActivityResult consume crashed");
                runOnUiThread(new Runnable(){
                    public void run(){
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, R.string.Error_Access_empty,Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }

        @Override
        public void onFailure(Call<List<PullRequest>> call, Throwable t) {
            t.printStackTrace();
        }
    };



    /**
     * Open connect with URL for get JSON  .
     */
    private void createStackOverflowAPI() {
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utilite.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mPullRequestsInterface = retrofit.create(PullRequestsInterface.class);
    }

    @Override
    public void onClick(PullRequest pullRequests) {

        openWebPage(pullRequests.getHtml_url());
    }

    /**
     * Open a web page of a specified URL
     *
     * @param url URL to open
     */
    public void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
