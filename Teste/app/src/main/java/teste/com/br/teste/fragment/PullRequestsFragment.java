package teste.com.br.teste.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import teste.com.br.teste.R;
import teste.com.br.teste.activity.MainActivity;
import teste.com.br.teste.adapter.PullRequestAdapter;
import teste.com.br.teste.application.MongeralApp;
import teste.com.br.teste.callback.PullRequestCallback;
import teste.com.br.teste.component.RepositoryComponent;
import teste.com.br.teste.delegate.Delegate;
import teste.com.br.teste.model.PullRequest;
import teste.com.br.teste.service.RepositoryService;
import teste.com.br.teste.util.Util;

public class PullRequestsFragment extends Fragment implements Delegate {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.name_repository)
    TextView nameRepository;

    @Inject
    RepositoryService service;

    private MainActivity activity;
    private List<PullRequest> pullRequests;
    private PullRequestAdapter adapter;
    private PullRequestCallback pullRequestCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pullrequest, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nameRepository.setText(getArguments().getString("name"));

        activity = (MainActivity) getActivity();
        activity.getMyToolbar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
            }
        });

        pullRequests = new ArrayList<PullRequest>();
        pullRequestCallback = new PullRequestCallback(this);

        getComponents();
        configureRecyclerView();
        searchPullRequests();
    }

    private void getComponents() {
        MongeralApp app = (MongeralApp) activity.getApplication();
        RepositoryComponent component = app.getComponent();
        component.inject(this);
    }

    private void configureRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayout);
    }

    private void searchPullRequests() {
        if (!Util.checkConnection(activity)) {
            Util.showToast(activity, getString(R.string.alert_no_internet));
            return;
        } else {
            Util.showProgressDiaolg(getActivity(), getString(R.string.searching_pullrequests), getString(R.string.wait), false);
            String name = getArguments().getString("name");
            String owner = getArguments().getString("owner");
            Call<List<PullRequest>> call = service.listPullRequests(owner, name);
            call.enqueue(pullRequestCallback);
        }
    }

    private void populateRecyclerView() {
        adapter = new PullRequestAdapter(activity, pullRequests);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void error() {
        Util.dimissProgressDialog();
        Util.showToast(activity, pullRequestCallback.getError());
    }

    @Override
    public void success() {
        Util.dimissProgressDialog();
        pullRequests.addAll(pullRequestCallback.getPullRequests());
        populateRecyclerView();
    }

}
