package com.innerforge.testapp.repository.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.innerforge.testapp.ui.NavigableFragment;
import com.innerforge.testapp.R;
import com.innerforge.testapp.repository.list.Repository;
import com.innerforge.testapp.repository.RepositoryViewModel;

import java.util.List;

/**
 * Created by LuizH on 03/11/2017.
 */

public class FragmentRepositoryDetail extends NavigableFragment {

    public static final String BUNDLE_KEY_REPOSITORY = "BUNDLE_KEY_REPOSITORY";

    private TextView tvErrorMessage;
    private RecyclerView rView;
    private RepositoryViewModel mViewModel;
    private ProgressBar progressBar;
    private Repository repository;
    private TextView tvOpen;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        repository = (Repository) args.getSerializable(BUNDLE_KEY_REPOSITORY);
    }

    private int countOpen(List<PullRequest> pulls){

        int count = 0;
        for (PullRequest pullRequest : pulls){
            if(pullRequest.getState() != null && pullRequest.getState().equals("open")){
                count++;
            }
        }
        return count;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.testapp_fragment_repository_detail, null);

        rView = root.findViewById(R.id.TestApp_FragmentRepositoryDetail_rc);
        tvErrorMessage = root.findViewById(R.id.TestApp_FragmentRepositoryDetail_tvError);
        progressBar = root.findViewById(R.id.TestApp_FragmentRepositoryDetail_progressBar);


        tvOpen = root.findViewById(R.id.TestApp_FragmentRepositoryDetail_tvOpen);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rView.setLayoutManager(linearLayoutManager);
        mViewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);
        mViewModel.getPullRequests().observe(this, apiResponse -> {

            if(apiResponse.getCode() != 200){
                if(apiResponse.getMessage() != null){
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.setText(apiResponse.getMessage());
                }
            }
            else {
                int count = countOpen(apiResponse.getData());

                String str = String.format(getContext().getString(R.string.testapp_repository_detail_fragment_count_text), count, apiResponse.getData().size() - count);
                tvOpen.setText(str);
                tvErrorMessage.setVisibility(View.INVISIBLE);
                AdapterPullRequests rcAdapter = new AdapterPullRequests(apiResponse.getData());
                rView.setAdapter(rcAdapter);
            }
            progressBar.setVisibility(View.INVISIBLE);
        });

        getNavigationListener().enableBackButton();
        getNavigationListener().setToolBarTitle(repository.getName());
        progressBar.setVisibility(View.VISIBLE);
        mViewModel.loadPullRequests(repository.getOwner().getLogin(), repository.getName());
        return root ;
    }
}
