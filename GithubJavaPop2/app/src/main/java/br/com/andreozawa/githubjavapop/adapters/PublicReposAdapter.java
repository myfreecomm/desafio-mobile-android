package br.com.andreozawa.githubjavapop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.andreozawa.githubjavapop.R;
import br.com.andreozawa.githubjavapop.model.PublicRepos;

/**
 * Created by andre.ozawa on 08/11/2017.
 */

public class PublicReposAdapter extends RecyclerView.Adapter<PublicReposAdapter.PublicReposViewHolder> {

    private List<PublicRepos> publicReposes;
    private Context context;
    private OnPublicRepoItemClick onPublicRepoItemClick;

    public interface OnPublicRepoItemClick {
        void onClick(PublicRepos publicRepos);
    }

    public PublicReposAdapter(List<PublicRepos> publicReposes, Context context, OnPublicRepoItemClick onPublicRepoItemClick) {
        this.publicReposes = publicReposes;
        this.context = context;
        this.onPublicRepoItemClick = onPublicRepoItemClick;
    }

    @Override
    public PublicReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.javapop_item_row, parent, false);

        return new PublicReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublicReposViewHolder holder, final int position) {
        holder.repoNameTv.setText(this.publicReposes.get(position).getName());
        holder.repoDescriptionTv.setText(this.publicReposes.get(position).getDescription());
        holder.forksTv.setText(String.valueOf(this.publicReposes.get(position).getQtForks()));
        holder.starsTv.setText(String.valueOf(this.publicReposes.get(position).getQtStars()));

        Picasso.with(this.context)
                .load(this.publicReposes.get(position).getOwner().getAvatarUrl())
                .placeholder(R.mipmap.ic_default_user)
                .into(holder.userIv);

        holder.usernameTv.setText(this.publicReposes.get(position).getOwner().getLogin());
        holder.userFullNameTv.setText(this.publicReposes.get(position).getOwner().getLogin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPublicRepoItemClick.onClick(publicReposes.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.publicReposes.size();
    }

    public void addPublicReposes(List<PublicRepos> publicReposes) {
        this.publicReposes.addAll(publicReposes);

        notifyDataSetChanged();
    }

    class PublicReposViewHolder extends RecyclerView.ViewHolder {

        public TextView repoNameTv;
        public TextView repoDescriptionTv;
        public TextView forksTv;
        public TextView starsTv;
        public ImageView userIv;
        public TextView usernameTv;
        public TextView userFullNameTv;

        public PublicReposViewHolder(View itemView) {
            super(itemView);

            this.repoNameTv = (TextView) itemView.findViewById(R.id.repo_name_tv);
            this.repoDescriptionTv = (TextView) itemView.findViewById(R.id.repo_description_tv);
            this.forksTv = (TextView) itemView.findViewById(R.id.forks_tv);
            this.starsTv = (TextView) itemView.findViewById(R.id.stars_tv);
            this.userIv = (ImageView) itemView.findViewById(R.id.user_iv);
            this.usernameTv = (TextView) itemView.findViewById(R.id.username_tv);
            this.userFullNameTv = (TextView) itemView.findViewById(R.id.user_fullname_tv);
        }
    }
}
