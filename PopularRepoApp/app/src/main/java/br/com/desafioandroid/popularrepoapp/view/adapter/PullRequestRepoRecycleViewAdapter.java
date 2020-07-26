package br.com.desafioandroid.popularrepoapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.R;
import br.com.desafioandroid.popularrepoapp.entity.PullRequestEntity;
import br.com.desafioandroid.popularrepoapp.view.activity.BaseActivity;
import br.com.desafioandroid.popularrepoapp.view.holder.PullRequestRepoViewHolder;

/**
 * Created by Dennys on 15/11/2017.
 */

public class PullRequestRepoRecycleViewAdapter extends RecyclerView.Adapter<PullRequestRepoViewHolder> {

    private List<PullRequestEntity> listPullRequestRepo;
    private Context context;

    public PullRequestRepoRecycleViewAdapter(List<PullRequestEntity> listPullRequestRepo) {
        this.listPullRequestRepo = listPullRequestRepo;
    }

    @Override
    public PullRequestRepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new PullRequestRepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pull_request_repos, null));
    }

    @Override
    public void onBindViewHolder(PullRequestRepoViewHolder holder, int position) {

        holder.txtViewPullRequestRepoName.setText(listPullRequestRepo.get(position).titlePullRequest);
        holder.txtViewPullRequestRepoUserName.setText(listPullRequestRepo.get(position).userEntity.login);
        ((BaseActivity) context).application.loadImageUrl(holder.imageViewPullRequestRepoAvatar, listPullRequestRepo.get(position).userEntity.avatarUrl);

        if(listPullRequestRepo.get(position).bodyPullRequest == null || listPullRequestRepo.get(position).bodyPullRequest.equals("")){
            holder.txtViewPullRequestRepoDescription.setVisibility(View.GONE);
        }

        String date = ((BaseActivity) context).application.formatDate(listPullRequestRepo.get(position).datePullRequest);
        if(date != null){
            holder.txtDate.setVisibility(View.VISIBLE);
            holder.txtDate.setText(date);
        }
        holder.txtViewPullRequestRepoDescription.setText(listPullRequestRepo.get(position).bodyPullRequest);

    }

    @Override
    public int getItemCount() {
        return listPullRequestRepo != null ? listPullRequestRepo.size() : 0;
    }
}
