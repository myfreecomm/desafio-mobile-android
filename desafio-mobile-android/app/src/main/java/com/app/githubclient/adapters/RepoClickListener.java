package com.app.githubclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.app.githubclient.R;
import com.app.githubclient.activities.PullsActivity;
import com.app.githubclient.models.Repository;

/**
 * Created by thaynan on 20/05/2016.
 */
public class RepoClickListener implements View.OnClickListener {
    private Context context;

    public RepoClickListener(Context context) {
        this.context = context;
    }


    @Override
    public void onClick(View v) {
        if (v.getTag() instanceof Repository) {
            //final View placeholderShotView = v.findViewById(R.id.placeholderShot);

            Repository repo = (Repository) v.getTag();
            Intent intent = new Intent(context, PullsActivity.class);
            intent.putExtra(PullsActivity.REPOSITORY_KEY, repo);
            context.startActivity(intent);
        }
    }
}
