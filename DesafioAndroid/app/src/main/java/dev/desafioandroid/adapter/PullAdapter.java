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
import dev.desafioandroid.api.pojo.PullRequest;
import dev.desafioandroid.util.ItemClickListener;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.PullHolder>{

    private Activity mActivity;
    private ItemClickListener mListener;
    private List<PullRequest> mPullRequests;

    public PullAdapter(Activity activity, ItemClickListener listener) {
        mActivity = activity;
        mListener = listener;
        mPullRequests = new ArrayList<>();
    }

    public void addAll(List<PullRequest> pulls) {
        mPullRequests.addAll(pulls);
        notifyDataSetChanged();
    }

    @Override
    public PullHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View pullView = inflater.inflate(R.layout.item_pull, parent, false);
        return new PullAdapter.PullHolder(pullView);
    }

    @Override
    public void onBindViewHolder(final PullHolder holder, int position) {
        final PullRequest pull = mPullRequests.get(position);

        holder.title.setText(pull.getTitle());
        holder.body.setText(pull.getBody());
        holder.login.setText(pull.getUser().getLogin());
        Picasso.with(mActivity).load(pull.getUser().getAvatarUrl()).into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(pull, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPullRequests.isEmpty() ? 0 : mPullRequests.size();
    }

    class PullHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView title;
        @BindView(R.id.body) TextView body;
        @BindView(R.id.pic) ImageView pic;
        @BindView(R.id.login) TextView login;

        PullHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}