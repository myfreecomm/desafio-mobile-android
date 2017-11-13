package com.innerforge.testapp.repository.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innerforge.testapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by LuizH on 12/11/2017.
 */

public class AdapterPullRequests extends RecyclerView.Adapter<AdapterPullRequests.RecyclerViewHolders> {

    private List<PullRequest> itemList;
    private Context context;

    public AdapterPullRequests(List<PullRequest> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.testapp_pullrequest_list_item, parent, false);
        context = parent.getContext();
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {

        holder.tvName.setText(itemList.get(position).getTitle());
        /*if(itemList.get(position).getMilestone() != null) {
            holder.tvDescription.setText(itemList.get(position).getMilestone().getDescription());
        }*/
        holder.tvDescription.setText(itemList.get(position).getBody());
        holder.tvUser.setText(itemList.get(position).getUser().getLogin());

        Picasso.with(context)
                .load(itemList.get(position).getUser().getAvatarUrl())
                //.placeholder(R.drawable.user_placeholder)
                .into(holder.ivUser);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public static class RecyclerViewHolders extends RecyclerView.ViewHolder{

        public TextView tvName;
        public TextView tvDescription;

        public ImageView ivUser;
        public TextView tvUser;

        public RecyclerViewHolders(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.TestApp_PullRequestListItem_tvName);
            tvDescription = itemView.findViewById(R.id.TestApp_PullRequestListItem_tvDescription);

            ivUser = itemView.findViewById(R.id.TestApp_PullRequestListItem_ivUser);
            tvUser = itemView.findViewById(R.id.TestApp_PullRequestListItem_tvUser);
        }
    }
}