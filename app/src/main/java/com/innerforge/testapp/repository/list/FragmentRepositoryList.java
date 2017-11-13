package com.innerforge.testapp.repository.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.innerforge.testapp.ui.EndlessScrollListener;
import com.innerforge.testapp.repository.detail.FragmentRepositoryDetail;
import com.innerforge.testapp.ui.NavigableFragment;
import com.innerforge.testapp.R;
import com.innerforge.testapp.repository.ActivityMain;
import com.innerforge.testapp.repository.RepositoryViewModel;

import java.util.ArrayList;

/**
 * Created by LuizH on 30/10/2017.
 */

public class FragmentRepositoryList extends NavigableFragment {

    private RecyclerView rView;
    private TextView tvErrorMessage;
    private RepositoryViewModel mViewModel;
    private ProgressBar progressBar;
    private AdapterRepositoryList rcAdapter;
    private EndlessScrollListener endlessScrollListener;
    private int currentPage = 0, currentPageAux = 1;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.testapp_fragment_repository_list, null);

        rView = root.findViewById(R.id.TestApp_FragmentRepositoryList_rc);
        tvErrorMessage = root.findViewById(R.id.TestApp_FragmentRepositoryList_tvError);
        progressBar = root.findViewById(R.id.TestApp_FragmentRepositoryList_progressBar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rView.setLayoutManager(linearLayoutManager);
        mViewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);

        rcAdapter = new AdapterRepositoryList(new ArrayList<>(), (view, repository, position) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(FragmentRepositoryDetail.BUNDLE_KEY_REPOSITORY, repository);
            getNavigationListener().navigate(ActivityMain.FRAGMENT_REPOSITORY_DETAIL_ID, bundle, repository.getName());
        });
        rView.setAdapter(rcAdapter);
        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                Log.d("TEST_APP", "onLoadMore");
                mViewModel.loadRepositories(current_page);
                currentPageAux = current_page;
            }
        };

        rView.addOnScrollListener(endlessScrollListener);

        mViewModel.getRepositoryListResponse().observe(this, apiResponse -> {

            if (apiResponse.getCode() != 200) {
                if (apiResponse.getMessage() != null) {
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.setText(apiResponse.getMessage());
                    currentPage = currentPageAux;
                }
            } else {

                tvErrorMessage.setVisibility(View.INVISIBLE);
                rcAdapter.setRepositoryList(apiResponse.getData().getRepositories());
            }
            endlessScrollListener.setCurrent_page(mViewModel.getLastPage());
            progressBar.setVisibility(View.INVISIBLE);
        });
        progressBar.setVisibility(View.VISIBLE);

        mViewModel.loadRepositories();
        setRetainInstance(true);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getNavigationListener().setToolBarTitle(getString(R.string.testapp_repository_fragment_name));
        getNavigationListener().disableBackButton();
    }
}