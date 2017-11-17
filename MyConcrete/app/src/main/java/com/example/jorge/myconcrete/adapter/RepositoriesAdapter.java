package com.example.jorge.myconcrete.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jorge.myconcrete.R;
import com.example.jorge.myconcrete.model.Repositories;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorge on 09/11/2017.
 */

/**
 * Class Adapter for support RecyclerView with list json in Activity
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>  {

    private final List<Repositories> data;

    private Context mContext;


    /*
 * An on-click handler that we've defined to make it easy for an Activity to interface with
 * our RecyclerView
 */
    private static RepositoriesAdapterOnClickHandler mClickHandler;
    /**
     * The interface that receives onClick messages.
     */
    public interface RepositoriesAdapterOnClickHandler {
        void onClick(Repositories repositories);
    }

    /** Constructs the class**/
    public  RepositoriesAdapter(RepositoriesAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
        data = null;
    }




    /** class view holder**/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.iv_avatar_url) ImageView mAvatarUrlImageView;
        @BindView(R.id.tv_name) TextView mNameTextView;
        @BindView(R.id.tv_description) TextView mDescriptionTextView;
        @BindView(R.id.tv_stargazers_count) TextView mStargazersCountTextView;
        @BindView(R.id.tv_forks_count) TextView mForksCountTextView;
        @BindView(R.id.tv_login) TextView mLoginTextView;
        @BindView(R.id.iv_forks) ImageView mForks;
        @BindView(R.id.iv_star) ImageView mStar;





        /** get field of the main for show recyclerView**/
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
            v.setOnClickListener(this);
        }

        /** configuration the Event onclick. Pass o Object Travel **/
        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Repositories repositories = data.get(adapterPosition);
            mClickHandler.onClick(repositories);

        }
    }

    /** create lit de Adapter Travel**/
    public RepositoriesAdapter(List<Repositories> data) {
        this.data = data;
    }

    /** Create information View holder**/
    @Override
    public RepositoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.informations_repositories, parent, false);
        mContext = parent.getContext();


        return new ViewHolder(v);
    }

    /** Create filed bind hold full **/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        /** Paint dynamic forks image and Star image**/
        Resources res = mContext.getResources();
        final int newColor = res.getColor(R.color.colorYellow);
        holder.mForks.setColorFilter(newColor, PorterDuff.Mode.SRC_ATOP);
        holder.mStar.setColorFilter(newColor, PorterDuff.Mode.SRC_ATOP);

        /** Create filed bind hold full **/

        Repositories repositories = ((Repositories) data.get(position));
        holder.mNameTextView.setText(repositories.getName());
        holder.mDescriptionTextView.setText(repositories.getDescription());
        holder.mStargazersCountTextView.setText(Integer.toString(repositories.getStargazers_count()));
        holder.mForksCountTextView.setText(Integer.toString(repositories.getForks_count()));
        holder.mLoginTextView.setText(repositories.getOwner().getLogin());
        holder.mDescriptionTextView.setText(repositories.getDescription());
        Picasso.with(mContext).load(repositories.getOwner().getAvatar_url()).into(holder.mAvatarUrlImageView);

    }

    /** Returns the total Adapter**/
    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<Repositories> getData() {
        return data;
    }




}
