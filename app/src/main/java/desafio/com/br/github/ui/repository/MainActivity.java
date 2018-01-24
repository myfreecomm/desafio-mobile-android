package desafio.com.br.github.ui.repository;


import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;

import butterknife.*;
import desafio.com.br.github.R;
import desafio.com.br.github.data.network.model.repository.*;
import desafio.com.br.github.util.*;

public class MainActivity extends AppCompatActivity implements IviewRepository {

    @BindView(R.id.repositories_list)
    RecyclerView repositoriesRecyclerView;
    IpresenterRepository.PresenterView presenterRepository;
    LinearLayoutManager layoutManager;
    Repository repository;
    repositoryAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    int listCurrentSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenterRepository = new PresenterRepository(this);
        presenterRepository.fetchData();

        layoutManager = new LinearLayoutManager(this);
        repositoriesRecyclerView.setLayoutManager(layoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                listCurrentSize = adapter.getItemCount();
                presenterRepository.fetchData();
            }
        };

        repositoriesRecyclerView.addOnScrollListener(scrollListener);


    }

    @Override
    public void Success(Repository repository) {

        if(this.repository!=null)
            this.repository.getItems().addAll(repository.getItems());
        else
            this.repository = repository;

        if(adapter==null)
        {
            adapter = new repositoryAdapter(repository,MainActivity.this);
            repositoriesRecyclerView.setAdapter(adapter);
        }else
        {
            adapter.notifyItemRangeChanged(listCurrentSize,this.repository.getItems().size()-1);
        }

    }


    @Override
    public void Failure(String error) {

    }
}
