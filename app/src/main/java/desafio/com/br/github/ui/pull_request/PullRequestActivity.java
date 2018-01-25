package desafio.com.br.github.ui.pull_request;

import android.app.*;
import android.os.*;
import android.support.v7.widget.*;
import java.util.*;
import butterknife.*;
import desafio.com.br.github.R;
import desafio.com.br.github.data.network.model.pull_request.*;


/**
 * Created by rafael on 24/01/18.
 */


public class PullRequestActivity extends Activity implements IviewPullRequest{
    @BindView(R.id.repositories_list)
    RecyclerView repositoriesRecyclerView;
    IpresenterPullRequest.PresenterView presenterPullRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        presenterPullRequest = new PresenterPullRequest(this);
        presenterPullRequest.fetchData(getIntent().getStringExtra("creator"),getIntent().getStringExtra("repository"));

        ButterKnife.bind(this);
    }

    @Override
    public void Success(ArrayList<PullRequest> pullRequests) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(PullRequestActivity.this);
        repositoriesRecyclerView.setLayoutManager(layoutManager);
        pullRequestAdapter adapter = new pullRequestAdapter(pullRequests,PullRequestActivity.this);
        repositoriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void Failure(String error) {

    }
}

