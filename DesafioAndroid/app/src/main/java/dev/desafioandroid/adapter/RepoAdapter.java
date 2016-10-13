package dev.desafioandroid.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.desafioandroid.R;
import dev.desafioandroid.api.pojo.Repo;
import dev.desafioandroid.util.ItemClickListener;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {

    private List<Repo> mRepos;
    private ItemClickListener mListener;
    private Activity mActivity;

    public RepoAdapter(Activity activity, ItemClickListener listener) {
        mActivity = activity;
        mListener = listener;
        mRepos = new ArrayList<>();
    }

    public void addAll(List<Repo> repos) {
        mRepos.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public RepoAdapter.RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View repoView = inflater.inflate(R.layout.item_repo, parent, false);
        return new RepoHolder(repoView);
    }

    @Override
    public void onBindViewHolder(final RepoHolder holder, int position) {
        final Repo repo = mRepos.get(position);

        holder.name.setText(repo.getName());
        holder.description.setText(repo.getDescription());
        holder.forks.setText(String.valueOf(repo.getForksCount()));
        holder.stars.setText(String.valueOf(repo.getStargazersCount()));
        holder.login.setText(repo.getOwner().getLogin());
        Picasso.with(mActivity).load(repo.getOwner().getAvatarUrl()).into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(repo, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRepos.isEmpty() ? 0 : mRepos.size();
    }

    class RepoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) TextView name;
        @BindView(R.id.desc) TextView description;
        @BindView(R.id.login) TextView login;
        @BindView(R.id.pic) ImageView pic;
        @BindView(R.id.forks) TextView forks;
        @BindView(R.id.stars) TextView stars;

        RepoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}