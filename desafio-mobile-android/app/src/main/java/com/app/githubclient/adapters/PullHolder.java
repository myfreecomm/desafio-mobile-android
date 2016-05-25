package com.app.githubclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.app.githubclient.R;
import com.app.githubclient.models.Pull;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by thaynan on 25/05/2016.
 */
public class PullHolder extends RecyclerView.ViewHolder {

    private Context context;
    private View itemView;

    private TextView titlePull;
    private TextView bodyPull;
    private SimpleDraweeView imageUser;
    private TextView username;
    private TextView created;

    public PullHolder(final View v, final Context cx) {
        super(v);
        this.itemView = v;
        this.context = cx;
        itemView.setOnClickListener(new RepoClickListener(context));

        setupView(itemView);
    }

    private void setupView(View iv) {
        titlePull = (TextView) iv.findViewById(R.id.title_pull);
        bodyPull = (TextView) iv.findViewById(R.id.body_pull);
        imageUser = (SimpleDraweeView) iv.findViewById(R.id.imageUser);
        username = (TextView) iv.findViewById(R.id.username);
        created = (TextView) iv.findViewById(R.id.created);
    }

    public TextView getTitlePull() {
        return titlePull;
    }

    public void setTitlePull(TextView titlePull) {
        this.titlePull = titlePull;
    }

    public TextView getBodyPull() {
        return bodyPull;
    }

    public void setBodyPull(TextView bodyPull) {
        this.bodyPull = bodyPull;
    }

    public SimpleDraweeView getImageUser() {
        return imageUser;
    }

    public void setImageUser(SimpleDraweeView imageUser) {
        this.imageUser = imageUser;
    }

    public TextView getUsername() {
        return username;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public TextView getCreated() {
        return created;
    }

    public void setCreated(TextView created) {
        this.created = created;
    }

    public void setPull(Pull pull) {
        this.itemView.setTag(pull);
    }
}
