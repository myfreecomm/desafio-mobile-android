package com.example.jorge.myconcrete;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.jorge.myconcrete.adapter.RepositoriesAdapter;
import com.example.jorge.myconcrete.adapter.RepositoriesAdapter.RepositoriesAdapterOnClickHandler;
import com.example.jorge.myconcrete.folderInterface.RepositoriesInterface;
import com.example.jorge.myconcrete.model.ListWrapper;
import com.example.jorge.myconcrete.model.Repositories;
import com.example.jorge.myconcrete.utilite.Common;
import com.example.jorge.myconcrete.utilite.EndlessRecyclerViewScrollListener;
import com.example.jorge.myconcrete.utilite.Utilite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Class Main list in RecyclerView all Repositories .
 */

public class MainActivity extends AppCompatActivity implements RepositoriesAdapterOnClickHandler {

    private final String KEY_RECYCLER_STATE = "recycler_state";
    private final String KEY_ADAPTER_STATE = "adapter_state";
    private static Bundle mBundleRecyclerViewState;

    RepositoriesAdapter mRepositoriesAdapter;

    private RepositoriesInterface mRepositorieInterface;

    @BindView(R.id.rv_repositories)
    RecyclerView mRecyclerView;

    private EndlessRecyclerViewScrollListener mScrollListener;

    LinearLayoutManager mLinearLayoutManager;

    private Parcelable listState;
    private ArrayList<Repositories> listStateAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Change findByID for  ButterKnife .
         */
        ButterKnife.bind(this);

        if (savedInstanceState == null) {

            initRecyclerView();

            mBundleRecyclerViewState = new Bundle();
            Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
            mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);

            /**
             * checks if internet is ok .
             */
            if (Common.isOnline(getSystemService(Context.CONNECTIVITY_SERVICE))) {
                createStackOverflowAPI();
                mRepositorieInterface.getRepositories("language:Java", "stars", Integer.toString(1)).enqueue(repositorieCallback);
            } else {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, R.string.Error_Access, Toast.LENGTH_SHORT);
                toast.show();
            }
        }else{
            //Get Save state the RecycleView and Adapter of the Repositories
            initRecyclerView();
            listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            listStateAdapter = (ArrayList<Repositories>) mBundleRecyclerViewState.getSerializable(KEY_ADAPTER_STATE);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
            mRepositoriesAdapter = new RepositoriesAdapter(listStateAdapter);
            mRecyclerView.setAdapter(mRepositoriesAdapter);

        }
    }

    public void loadNextDataFromApi(int offset) {

        if (Common.isOnline(getSystemService(Context.CONNECTIVITY_SERVICE))) {
            createStackOverflowAPI();
            mRepositorieInterface.getRepositories("language:Java", "stars", Integer.toString(offset)).enqueue(repositorieCallback);
        } else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, R.string.Error_Access, Toast.LENGTH_SHORT);
            toast.show();
        }



    }



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

        mRepositorieInterface = retrofit.create(RepositoriesInterface.class);
    }

    /**
     * Call Get Information Repositories .
     */
    private Callback<ListWrapper<Repositories>> repositorieCallback = new Callback<ListWrapper<Repositories>>() {
        @Override
        public void onResponse(Call<ListWrapper<Repositories>> call, Response<ListWrapper<Repositories>> response) {
            try {
                if (response.isSuccessful()) {
                    List<Repositories> data = new ArrayList<>();
                    data.addAll(response.body().items);

                    if (mRecyclerView.getAdapter() != null) {
                        List<Repositories> dataOld = mRepositoriesAdapter.getData();
                        data.addAll(dataOld);
                    }
                    mRepositoriesAdapter = new RepositoriesAdapter(data);
                    mRecyclerView.setAdapter(mRepositoriesAdapter);

                } else {
                    Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
                }
            } catch (NullPointerException e) {
                System.out.println("onActivityResult consume crashed");
                runOnUiThread(new Runnable() {
                    public void run() {
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, R.string.Error_Access_empty, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Repositories>> call, Throwable t) {
            t.printStackTrace();
        }
    };


    /**
     * If click in Repositorie open Detail. Pass for Detail Name and Login through PutExtra.
     */
    @Override
    public void onClick(Repositories repositories) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Utilite.PUT_EXTRA_NAME, repositories.getName());
        intentToStartDetailActivity.putExtra(Utilite.PUT_EXTRA_LOGIN, repositories.getOwner().getLogin());
        startActivity(intentToStartDetailActivity);
    }


    private void initRecyclerView() {
        /**
         * use RecyclerView for list the Repositories .
         */
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRepositoriesAdapter = new RepositoriesAdapter(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mScrollListener = new EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                loadNextDataFromApi(page);
            }

        };
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        listStateAdapter = (ArrayList<Repositories>) mRepositoriesAdapter.getData();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
        mBundleRecyclerViewState.putSerializable(KEY_ADAPTER_STATE, listStateAdapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            listStateAdapter = (ArrayList<Repositories>) mBundleRecyclerViewState.getSerializable(KEY_ADAPTER_STATE);

        }
    }



}
