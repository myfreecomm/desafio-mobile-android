package br.com.andreozawa.githubjavapop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.andreozawa.githubjavapop.R;
import br.com.andreozawa.githubjavapop.model.PullRequest;

/**
 * Created by andre.ozawa on 08/11/2017.
 */

public class PullRequestsAdapter extends RecyclerView.Adapter<PullRequestsAdapter.PullRequestsViewHolder> {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private List<PullRequest> pullRequests;
    private Context context;
    private OnPullRequestItemClickListener onPullRequestItemClickListener;

    public interface OnPullRequestItemClickListener {
        void onClick(PullRequest pullRequest);
    }

    public PullRequestsAdapter(List<PullRequest> pullRequests, Context context, OnPullRequestItemClickListener onPullRequestItemClickListener) {
        this.pullRequests = pullRequests;
        this.context = context;
        this.onPullRequestItemClickListener = onPullRequestItemClickListener;
    }

    @Override
    public PullRequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.pull_request_item_row, parent, false);

        return new PullRequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PullRequestsViewHolder holder, final int position) {
        holder.titleTv.setText(this.pullRequests.get(position).getTitle());
        holder.descriptionTv.setText(this.pullRequests.get(position).getBody());
        holder.dateTv.setText(sdf.format(this.pullRequests.get(position).getPullRequestDate()));

        Picasso.with(this.context)
                .load(this.pullRequests.get(position).getUser().getAvatarUrl())
                .placeholder(R.mipmap.ic_default_user)
                .into(holder.authorIv);

        holder.usernameTv.setText(this.pullRequests.get(position).getUser().getLogin());
        holder.fullanmeTv.setText(this.pullRequests.get(position).getUser().getLogin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPullRequestItemClickListener.onClick(pullRequests.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.pullRequests.size();
    }

    public void setPullRequests(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;

        notifyDataSetChanged();
    }

    class PullRequestsViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTv;
        public TextView descriptionTv;
        public TextView dateTv;
        public ImageView authorIv;
        public TextView usernameTv;
        public TextView fullanmeTv;

        public PullRequestsViewHolder(View itemView) {
            super(itemView);

            this.titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            this.descriptionTv = (TextView) itemView.findViewById(R.id.description_tv);
            this.dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            this.authorIv = (ImageView) itemView.findViewById(R.id.author_iv);
            this.usernameTv = (TextView) itemView.findViewById(R.id.username_tv);
            this.fullanmeTv = (TextView) itemView.findViewById(R.id.fullname_tv);
        }
    }
}
