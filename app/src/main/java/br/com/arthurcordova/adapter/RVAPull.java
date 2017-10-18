package br.com.arthurcordova.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.arthurcordova.DetailActivity;
import br.com.arthurcordova.R;
import br.com.arthurcordova.model.Items;
import br.com.arthurcordova.model.PullRequestModel;
import br.com.arthurcordova.tools.CropImage;

/**
 * Created by acstapassoli on 17/10/17.
 */

public class RVAPull extends RecyclerView.Adapter<RVAPull.ViewHolder>{

    private List<PullRequestModel> mList;

    public RVAPull(List<PullRequestModel> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pull, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int index) {
        final PullRequestModel model = mList.get(index);
        holder.tvName.setText(model.getTitle());
        holder.tvDesc.setText(model.getBody());
        holder.tvOwnerName.setText(model.getUser().getLogin());

        Picasso.with(holder.imgAvatar.getContext())
                .load(model.getUser().getAvatar_url())
                .resize(160, 160)
                .placeholder(R.drawable.sem_foto)
                .transform(new CropImage())
                .into(holder.imgAvatar);

        holder.viewClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getHtml_url()));
                view.getContext().startActivity(browserIntent);
            }
        });

    }

    public void setFilter(List<PullRequestModel> list) {
        if (!mList.isEmpty()) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDesc;
        TextView tvOwnerName;
        ImageView imgAvatar;
        View viewClickable;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_repo_name);
            tvDesc = view.findViewById(R.id.tv_repo_desc);
            tvOwnerName = view.findViewById(R.id.tv_owner_name);
            imgAvatar = view.findViewById(R.id.image_avatar);
            viewClickable = view.findViewById(R.id.view_clickable);
        }
    }
}