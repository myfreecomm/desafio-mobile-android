package com.example.jorge.myconcrete.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jorge.myconcrete.R;
import com.example.jorge.myconcrete.model.PullRequest;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorge on 09/11/2017.
 */

public class PullRequestsAdapter extends RecyclerView.Adapter<PullRequestsAdapter.ViewHolder> {

    private List<PullRequest> data;

    private Context mContext;


    /*
 * An on-click handler that we've defined to make it easy for an Activity to interface with
 * our RecyclerView
 */
    private static PullRequestsAdapter.PullRequestsAdapterOnClickHandler mClickHandler;
    /**
     * The interface that receives onClick messages.
     */
    public interface PullRequestsAdapterOnClickHandler {
        void onClick(PullRequest pullRequests);
    }

    /** Constructs the class**/
    public  PullRequestsAdapter(PullRequestsAdapter.PullRequestsAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }


    /** class view holder**/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.iv_avatar_url)
        ImageView mAvatarUrlImageView;
        @BindView(R.id.tv_title)
        TextView mTitleTextView;
        @BindView(R.id.tv_html_url)
        TextView mHtml_urlTextView;
        @BindView(R.id.tv_created_at)
        TextView mCreated_atTextView;
        @BindView(R.id.tv_body)
        TextView mBodyTextView;
        @BindView(R.id.tv_login)
        TextView mLoginTextView;

        /** get field of the main for show RecyclerView**/
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
            v.setOnClickListener(this);
        }

        /** configuration the Event onclick. Pass o Object Travel **/
        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            PullRequest pullRequest = data.get(adapterPosition);
            mClickHandler.onClick(pullRequest);

        }
    }

    /** create lit de Adapter Travel**/
    public PullRequestsAdapter(List<PullRequest> data) {
        this.data = data;
    }

    /** Create information View holder**/
    @Override
    public PullRequestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.informations_pull_requests, parent, false);
        mContext = parent.getContext();


        return new PullRequestsAdapter.ViewHolder(v);
    }

    /** Create filed bind hold full **/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PullRequest pullRequest = ((PullRequest) data.get(position));
        holder.mTitleTextView.setText(pullRequest.getTitle());
        holder.mHtml_urlTextView.setText(pullRequest.getHtml_url());
        holder.mCreated_atTextView.setText(pullRequest.getCreated_at());
        holder.mBodyTextView.setText((pullRequest.getUser().getLogin()));
        holder.mLoginTextView.setText((pullRequest.getUser().getLogin()));

        Picasso.with(mContext).load(pullRequest.getUser().getAvatar_url()).into(holder.mAvatarUrlImageView);
    }


    /** Returns the total Adapter**/
    @Override
    public int getItemCount() {
        return data.size();
    }



}
