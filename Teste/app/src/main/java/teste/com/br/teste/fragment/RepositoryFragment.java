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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import teste.com.br.teste.R;
import teste.com.br.teste.activity.MainActivity;
import teste.com.br.teste.adapter.RepositoryAdapter;
import teste.com.br.teste.application.MongeralApp;
import teste.com.br.teste.callback.RepositoryCallback;
import teste.com.br.teste.component.RepositoryComponent;
import teste.com.br.teste.delegate.Delegate;
import teste.com.br.teste.listener.PaginateListener;
import teste.com.br.teste.model.Items;
import teste.com.br.teste.model.Repository;
import teste.com.br.teste.service.RepositoryService;
import teste.com.br.teste.util.Util;

public class RepositoryFragment extends Fragment implements Delegate {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    RepositoryService service;

    MainActivity activity;
    List<Repository> repositories;
    RepositoryAdapter adapter;
    RepositoryCallback repositoryCallback;
    Integer page;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repository, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) getActivity();
        activity.getMyToolbar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
            }
        });

        repositories = new ArrayList<Repository>();
        repositoryCallback = new RepositoryCallback(this);
        page = 1;

        getComponents();
        configureRecyclerView();
        searchRepositories();
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
        recyclerView.setOnScrollListener(new PaginateListener(linearLayout) {
            @Override
            public void onLoadMore(int current_page) {
                page++;
                searchRepositories();
            }
        });
    }

    private void searchRepositories() {
        if (!Util.checkConnection(activity)) {
            Util.showToast(activity, getString(R.string.alert_no_internet));
            return;
        } else {
            Util.showProgressDiaolg(getActivity(), getString(R.string.searching_repositories), getString(R.string.wait), false);
            Call<Items> call = service.listRepositories("Swift", "starts", page);
            call.enqueue(repositoryCallback);
        }
    }

    private void populateRecyclerView() {
        if (adapter == null) {
            adapter = new RepositoryAdapter(getActivity(), repositories);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.refreshDrawableState();
        }
    }

    private void atualizarRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error() {
        Util.dimissProgressDialog();
        Util.showToast(activity, repositoryCallback.getError());
    }

    @Override
    public void success() {
        Util.dimissProgressDialog();
        repositories.addAll(repositoryCallback.getRepositories());
        if (page == 1) {
            populateRecyclerView();
        } else {
            atualizarRecyclerView();
        }
    }

}
