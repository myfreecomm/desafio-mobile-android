package br.com.desafioandroid.popularrepoapp.view.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.R;
import br.com.desafioandroid.popularrepoapp.entity.ItemRepositoryEntity;
import br.com.desafioandroid.popularrepoapp.view.activity.BaseActivity;
import br.com.desafioandroid.popularrepoapp.view.holder.RepositoryViewHolder;

/**
 * Created by Dennys on 15/11/2017.
 */

public class RepositoryRecycleViewAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {

    private List<ItemRepositoryEntity> listRepositoryEntities;
    private Context context;

    public RepositoryRecycleViewAdapter(List<ItemRepositoryEntity> listRepositoryEntities) {
        this.listRepositoryEntities = listRepositoryEntities;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new RepositoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, null));
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {

        holder.txtViewRepositoryName.setText(listRepositoryEntities.get(position).name);
        holder.txtUserName.setText(listRepositoryEntities.get(position).owner.login);
        holder.txtCountForks.setText(String.valueOf(listRepositoryEntities.get(position).forkCount));
        holder.txtCountStarts.setText(String.valueOf(listRepositoryEntities.get(position).startCount));
        holder.imageViewFork.setColorFilter(ContextCompat.getColor(context, R.color.colorGoldenrod), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.imageViewStart.setColorFilter(ContextCompat.getColor(context, R.color.colorGoldenrod), android.graphics.PorterDuff.Mode.SRC_IN);

        if(listRepositoryEntities.get(position).description == null || listRepositoryEntities.get(position).description.equals("")){
            holder.txtViewDescription.setVisibility(View.GONE);
        }else{
            holder.txtViewDescription.setText(listRepositoryEntities.get(position).description);
        }

        ((BaseActivity) context).application.loadImageUrl(holder.imageViewAvatar, listRepositoryEntities.get(position).owner.avatarUrl);

    }

    @Override
    public int getItemCount() {
        return listRepositoryEntities != null ? listRepositoryEntities.size() : 10;
    }

    public List<ItemRepositoryEntity> getItems() {
        return this.listRepositoryEntities;
    }
}
