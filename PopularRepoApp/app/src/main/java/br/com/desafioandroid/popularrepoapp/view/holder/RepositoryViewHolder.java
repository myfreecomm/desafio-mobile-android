package br.com.desafioandroid.popularrepoapp.view.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.desafioandroid.popularrepoapp.R;
import br.com.desafioandroid.popularrepoapp.entity.ItemRepositoryEntity;
import br.com.desafioandroid.popularrepoapp.view.activity.MainActivity;
import br.com.desafioandroid.popularrepoapp.view.activity.PullRequestReposAcitivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dennys on 15/11/2017.
 */

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_user_avatar) public ImageView imageViewAvatar;
    @BindView(R.id.txt_user_name) public TextView txtUserName;
    @BindView(R.id.image_view_fork) public ImageView imageViewFork;
    @BindView(R.id.image_view_start) public ImageView imageViewStart;
    @BindView(R.id.txt_view_repository_name) public TextView txtViewRepositoryName;
    @BindView(R.id.txt_description) public TextView txtViewDescription;
    @BindView(R.id.txt_count_forks) public TextView txtCountForks;
    @BindView(R.id.txt_count_starts) public TextView txtCountStarts;

    public RepositoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    @OnClick(R.id.relative_layout)
    public void loadPullRequestRepoActivity(View view){

        Intent intent = new Intent(itemView.getContext(), PullRequestReposAcitivity.class);
        ItemRepositoryEntity iteRepositoryEntity= ((MainActivity)itemView.getContext()).presenterHome.listItenItemRepositoryEntities.get(getAdapterPosition());
        intent.putExtra(PullRequestReposAcitivity.REPOSITORY_ENTITY, iteRepositoryEntity);
        itemView.getContext().startActivity(intent);

    }
}
