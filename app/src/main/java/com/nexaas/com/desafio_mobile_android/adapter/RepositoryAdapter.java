package com.nexaas.com.desafio_mobile_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexaas.com.desafio_mobile_android.R;
import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.CustomViewHolder> {

    private ArrayList<RepositoryEntity> list;
    private OnItemClickListener onItemClickListener;
    private Context context;

//    public RepositoryAdapter(List<RepositoryEntity> list, Context context){
//        this.list = list;
//        this.context = context;
//    }

    public RepositoryAdapter(ArrayList<RepositoryEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repository, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        RepositoryEntity repositoryEntity = this.list.get(position);
        holder.nameTextView.setText(repositoryEntity.getName());
        holder.descTextView.setText(repositoryEntity.getDescription());
        holder.userNameTextView.setText(repositoryEntity.getOwner().getLogin());
        if (repositoryEntity.getForks_count() != null)
            holder.forksTextView.setText(String.valueOf(repositoryEntity.getForks_count()));
        if (repositoryEntity.getStargazers_count() != null)
            holder.starsTextView.setText(String.valueOf(repositoryEntity.getStargazers_count()));
        Picasso.with(this.context).load(repositoryEntity.getOwner().getAvatar_url()).error(context.getResources().getDrawable(R.drawable.ic_no_avatar)).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView descTextView;
        public TextView userNameTextView;
        public TextView forksTextView;
        public TextView starsTextView;
        public ImageView imgView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.nameTextView);
            this.descTextView = itemView.findViewById(R.id.descTextView);
            this.userNameTextView = itemView.findViewById(R.id.userNameTextView);
            this.forksTextView = itemView.findViewById(R.id.forksTextView);
            this.starsTextView = itemView.findViewById(R.id.starsTextView);
            this.imgView = itemView.findViewById(R.id.imgView);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(list.get(getLayoutPosition()));
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(RepositoryEntity item);
    }

    public void addAll(ArrayList<RepositoryEntity> list) {
        for (RepositoryEntity item : list) {
            this.list.add(item);
            notifyDataSetChanged();
        }
    }
}

