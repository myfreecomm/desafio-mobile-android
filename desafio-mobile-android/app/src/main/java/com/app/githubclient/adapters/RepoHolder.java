package com.app.githubclient.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.githubclient.R;
import com.app.githubclient.models.Repository;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by thaynan on 20/05/2016.
 */
public class RepoHolder extends RecyclerView.ViewHolder {

    private Context context;
    private View itemView;

    private TextView nameRepo;
    private TextView descriptionRepo;
    private TextView forks;
    private TextView stars;
    private SimpleDraweeView imageUser;
    private TextView username;

    public RepoHolder(final View v, final Context cx) {
        super(v);
        this.itemView = v;
        this.context = cx;
        itemView.setOnClickListener(new RepoClickListener(context));

        setupView(itemView);
    }

    private void setupView(View itemView) {
        nameRepo = (TextView)itemView.findViewById( R.id.name_repo );
        descriptionRepo = (TextView)itemView.findViewById( R.id.description_repo );
        forks = (TextView)itemView.findViewById( R.id.forks );
        stars = (TextView)itemView.findViewById( R.id.stars );
        imageUser = (SimpleDraweeView )itemView.findViewById( R.id.imageUser );
        username = (TextView)itemView.findViewById( R.id.username );


        //shotImage.setOnClickListener();
    }

    public TextView getNameRepo() {
        return nameRepo;
    }

    public void setNameRepo(TextView nameRepo) {
        this.nameRepo = nameRepo;
    }

    public TextView getDescriptionRepo() {
        return descriptionRepo;
    }

    public void setDescriptionRepo(TextView descriptionRepo) {
        this.descriptionRepo = descriptionRepo;
    }

    public TextView getForks() {
        return forks;
    }

    public void setForks(TextView forks) {
        this.forks = forks;
    }

    public TextView getStars() {
        return stars;
    }

    public void setStars(TextView stars) {
        this.stars = stars;
    }

    public SimpleDraweeView  getImageUser() {
        return imageUser;
    }

    public void setImageUser(SimpleDraweeView  imageUser) {
        this.imageUser = imageUser;
    }

    public TextView getUsername() {
        return username;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public void setRepo(Repository repo) {
        this.itemView.setTag(repo);
    }
}
