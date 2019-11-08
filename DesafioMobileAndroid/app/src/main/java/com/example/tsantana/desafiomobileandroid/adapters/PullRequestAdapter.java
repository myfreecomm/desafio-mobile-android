package com.example.tsantana.desafiomobileandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tsantana.desafiomobileandroid.R;
import com.example.tsantana.desafiomobileandroid.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.tsantana.desafiomobileandroid.data.model.PullRequest;

/**
 * Created by tsantana on 07/12/2017.
 */

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder> {
    private ArrayList<PullRequest> mPullRequests;
    private Context context;

    public PullRequestAdapter(ArrayList<PullRequest> pullRequests, Context context){
        this.mPullRequests = pullRequests;
        this.context = context;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_second,parent,false);
        return new PullRequestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PullRequestViewHolder holder, int position) {
        PullRequest pullRequest = mPullRequests.get(position);
        holder.tvTituloPull.setText(pullRequest.getTitulo());
        holder.tvBodyPull.setText(pullRequest.getBody());
        holder.tvUserNamePull.setText(pullRequest.getAutor().getLogin());
        Picasso.with(context)
                .load(pullRequest.getAutor().getFoto())
                .into(holder.ivFotoPull);
        holder.tvDataPull.setText(pullRequest.getDataFormatada());
        holder.url = pullRequest.getUrl();

    }

    @Override
    public int getItemCount() {
        return mPullRequests.size();
    }

    protected static class PullRequestViewHolder extends RecyclerView.ViewHolder{
        protected TextView tvTituloPull;
        protected TextView tvBodyPull;
        protected CircleImageView ivFotoPull;
        protected TextView tvUserNamePull;
        protected TextView tvDataPull;
        protected String url;

        public PullRequestViewHolder(final View itemView) {
            super(itemView);
            tvTituloPull = (TextView) itemView.findViewById(R.id.tituloPull);
            tvBodyPull =(TextView) itemView.findViewById(R.id.bodyPull);
            ivFotoPull =(CircleImageView) itemView.findViewById(R.id.fotoPull);
            tvUserNamePull = (TextView) itemView.findViewById(R.id.userNamePull);
            tvDataPull = (TextView) itemView.findViewById(R.id.dataPull);
            url ="";

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (CommonUtils.isNetworkAvailable(itemView.getContext())) {

                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        itemView.getContext().startActivity(intent);

                    }
                }
            });
        }


    }
}

