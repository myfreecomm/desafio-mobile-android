package com.nexaas.com.desafio_mobile_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexaas.com.desafio_mobile_android.R;
import com.nexaas.com.desafio_mobile_android.model.PullRequestEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.CustomViewHolder>{

    private List<PullRequestEntity> list;
    private Context context;

    public PullRequestAdapter(List<PullRequestEntity> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pull_request, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PullRequestAdapter.CustomViewHolder holder, int position) {
        PullRequestEntity pullRequestEntity = this.list.get(position);
        holder.nameTextView.setText(pullRequestEntity.getTitle());
        holder.descTextView.setText(pullRequestEntity.getBody());
        holder.userNameTextView.setText(pullRequestEntity.getUser().getLogin());
        holder.dateTextView.setText(pullRequestEntity.getCreated_at());
        Picasso.with(this.context).load(pullRequestEntity.getUser().getAvatar_url()).error(context.getResources().getDrawable(R.drawable.ic_no_avatar)).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView descTextView;
        public TextView userNameTextView;
        public TextView dateTextView;
        public ImageView imgView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.nameTextView);
            this.descTextView = itemView.findViewById(R.id.descTextView);
            this.dateTextView = itemView.findViewById(R.id.dateTextView);
            this.userNameTextView = itemView.findViewById(R.id.userNameTextView);
            this.imgView = itemView.findViewById(R.id.imgView);

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String endereco = list.get(getLayoutPosition()).getHtml_url();
                    Uri uri = Uri.parse(endereco);

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                    context.startActivity(intent);
                }
            });

        }
    }
}
