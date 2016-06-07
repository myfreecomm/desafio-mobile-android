package com.app.githubclient.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.githubclient.R;
import com.app.githubclient.models.Pull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thaynan on 25/05/2016.
 */
public class PullsListAdapter extends RecyclerView.Adapter<PullHolder> {
    private List<Pull> item;
    //private RepoClickListener RepoListener;
    private Context context;

    public PullsListAdapter(Context context) {
        super();
        this.context = context;
        //RepoListener = new RepoClickListener(context);
    }

    @Override
    public PullHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pulls, parent, false);
        return new PullHolder(v, context);
    }

    @Override
    public void onBindViewHolder(PullHolder holder, int position) {
        Pull pull = item.get(position);
        holder.setPull(pull);
        holder.getTitlePull().setText(pull.getTitle());
        holder.getBodyPull().setText(Html.fromHtml(pull.getBody() == null ? "" : pull.getBody()));
        holder.getUsername().setText(pull.getUser().getLogin());
        holder.getCreated().setText(pull.getCreated());
        //holder.getShotDescription().setText(Html.fromHtml(shot.getDescription() == null ? "" : shot.getDescription()));
        holder.getImageUser().setImageURI(Uri.parse(pull.getUser().getAvatar_url()));
    }

    public void addPulls(List<Pull> Pulls) {
        if (item == null)
            item = new ArrayList<Pull>();
        item.addAll(Pulls);
        notifyDataSetChanged();
    }

    public void addNewPulls(List<Pull> Pulls) {
        if (item == null)
            item = new ArrayList<Pull>();
        item.clear();
        item.addAll(Pulls);
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
