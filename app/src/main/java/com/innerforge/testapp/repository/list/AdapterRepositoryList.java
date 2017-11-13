package com.innerforge.testapp.repository.list;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innerforge.testapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

/**
 * Created by LuizH on 31/10/2017.
 */

public class AdapterRepositoryList extends RecyclerView.Adapter<AdapterRepositoryList.RecyclerViewHolders> {

    private List<Repository> itemList;
    private ItemClickListener itemClickListener;
    private Context context;

    public interface ItemClickListener{
        void onClick(View view, Repository repository, int position);
    }

    public AdapterRepositoryList(List<Repository> itemList, ItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.testapp_repository_list_item, parent, false);
        context = parent.getContext();
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    public void setRepositoryList(final List<Repository> productList) {
        if (itemList == null) {
            itemList = productList;
            notifyItemRangeInserted(0, productList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return itemList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return itemList.get(oldItemPosition).getId() ==
                            productList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Repository newProduct = productList.get(newItemPosition);
                    Repository oldProduct = itemList.get(oldItemPosition);
                    return newProduct.getId() == oldProduct.getId();
                }
            });
            itemList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {

        holder.tvName.setText(itemList.get(position).getName());
        holder.tvDescription.setText(itemList.get(position).getDescription());
        holder.tvForks.setText(""+itemList.get(position).getForksCount());
        holder.tvStars.setText(""+itemList.get(position).getStargazersCount());
        holder.tvOwnerName.setText(itemList.get(position).getOwner().getLogin());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickListener != null){
                    itemClickListener.onClick(view, itemList.get(position), position);
                }
            }
        });
        Picasso.with(context)
                .load(itemList.get(position).getOwner().getAvatarUrl())
                //.placeholder(R.drawable.user_placeholder)
                .into(holder.ivOwner);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public static class RecyclerViewHolders extends RecyclerView.ViewHolder{

        public TextView tvName;
        public TextView tvDescription;
        public TextView tvForks;
        public TextView tvStars;

        public ImageView ivOwner;
        public TextView tvOwnerName;
        //public TextView tvOwnerFullName;

        public RecyclerViewHolders(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.TestApp_FragmentRepositoryListItem_tvName);
            tvDescription = itemView.findViewById(R.id.TestApp_FragmentRepositoryListItem_tvDescription);
            tvForks = itemView.findViewById(R.id.TestApp_FragmentRepositoryListItem_tvForks);
            tvStars = itemView.findViewById(R.id.TestApp_FragmentRepositoryListItem_tvStars);

            ivOwner = itemView.findViewById(R.id.TestApp_FragmentRepositoryListItem_ivOwner);
            tvOwnerName = itemView.findViewById(R.id.TestApp_FragmentRepositoryListItem_tvOwnerName);
            //tvOwnerFullName = itemView.findViewById(R.id.TestApp_FragmentRepositoryListItem_tvOwnerFullName);
        }
    }
}