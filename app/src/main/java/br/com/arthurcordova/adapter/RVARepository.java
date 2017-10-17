package br.com.arthurcordova.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.arthurcordova.R;
import br.com.arthurcordova.model.Items;
import br.com.arthurcordova.tools.CropImage;

/**
 * Created by acstapassoli on 17/10/17.
 */

public class RVARepository extends RecyclerView.Adapter<RVARepository.ViewHolder> {

    private List<Items> mList;

    public RVARepository(List<Items> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repository, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int index) {
        final Items model = mList.get(index);
        holder.tvName.setText(model.getName());
        holder.tvDesc.setText(model.getDescription());
        holder.tvForks.setText(String.valueOf(model.getForks()));
        holder.tvStargazers.setText(String.valueOf(model.getStargazers_count()));
        holder.tvOwnerName.setText(String.valueOf(model.getOwner().getLogin()));
        Picasso.with(holder.imgAvatar.getContext()).load(model.getOwner().getAvatar_url()).into(holder.imgAvatar);

        Picasso.with(holder.imgAvatar.getContext())
                .load(model.getOwner().getAvatar_url())
                .resize(160, 160)
                .placeholder(R.drawable.sem_foto)
                .transform(new CropImage())
                .into(holder.imgAvatar);

    }

    public void setFilter(List<Items> list) {
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
        TextView tvForks;
        TextView tvStargazers;
        ImageView imgAvatar;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_repo_name);
            tvDesc = view.findViewById(R.id.tv_repo_desc);
            tvForks = view.findViewById(R.id.tv_forks);
            tvOwnerName = view.findViewById(R.id.tv_owner_name);
            tvStargazers = view.findViewById(R.id.tv_stargazers);
            imgAvatar = view.findViewById(R.id.image_avatar);

        }
    }
}