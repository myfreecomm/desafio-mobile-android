package com.app.githubclient.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.githubclient.R;
import com.app.githubclient.models.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thaynan on 20/05/2016.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoHolder> {
    private List<Repository> item;
    private RepoClickListener RepoListener;
    private Context context;

    public RepoListAdapter(Context context) {
        super();
        this.context = context;
        RepoListener = new RepoClickListener(context);
    }

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new RepoHolder(v, context);
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
        Repository repo = item.get(position);
        holder.setRepo(repo);
        holder.getNameRepo().setText(repo.getName());
        holder.getDescriptionRepo().setText(repo.getDescription());
        holder.getForks().setText(String.valueOf(repo.getForks()));
        holder.getStars().setText(String.valueOf(repo.getStars()));
        holder.getUsername().setText(repo.getOwner().getLogin());
        //holder.getShotDescription().setText(Html.fromHtml(shot.getDescription() == null ? "" : shot.getDescription()));
        holder.getImageUser().setImageURI(Uri.parse(repo.getOwner().getAvatar_url()));
    }

    public void addRepos(List<Repository> Repos) {
        if (item == null)
            item = new ArrayList<Repository>();
        item.addAll(Repos);
        notifyDataSetChanged();
    }

    public void addNewRepos(List<Repository> Repos) {
        if (item == null)
            item = new ArrayList<Repository>();
        item.clear();
        item.addAll(Repos);
        notifyDataSetChanged();
    }

    public void clear(){
        item.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (item == null) {
            return 0;
        }
        return item.size();
    }
}
