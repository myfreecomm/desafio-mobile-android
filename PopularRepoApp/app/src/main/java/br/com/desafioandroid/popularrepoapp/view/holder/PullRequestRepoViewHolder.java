package br.com.desafioandroid.popularrepoapp.view.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.desafioandroid.popularrepoapp.R;
import br.com.desafioandroid.popularrepoapp.view.activity.PullRequestReposAcitivity;
import br.com.desafioandroid.popularrepoapp.view.activity.WebActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dennys on 15/11/2017.
 */

public class PullRequestRepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_user_avatar) public ImageView imageViewPullRequestRepoAvatar;
    @BindView(R.id.txt_view_pull_request_name) public TextView txtViewPullRequestRepoName;
    @BindView(R.id.txt_user_name) public TextView txtViewPullRequestRepoUserName;
    @BindView(R.id.txt_description_pull_request) public TextView txtViewPullRequestRepoDescription;
    @BindView(R.id.txt_date) public TextView txtDate;

    public PullRequestRepoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.linear_layout_inf_repository)
    public void loadWebActivity(View view){

        String url = ((PullRequestReposAcitivity) itemView.getContext()).pullRequestRepoPresenter.listPullRequestRepo.get(getAdapterPosition()).url;
        Intent intent = new Intent(itemView.getContext(), WebActivity.class);
        intent.putExtra(WebActivity.URL_PULL, url);
        itemView.getContext().startActivity(intent);
    }
}
