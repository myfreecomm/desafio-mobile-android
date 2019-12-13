package br.com.nexas.appgithubjava.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.nexas.appgithubjava.R;
import br.com.nexas.appgithubjava.modelo.PullRequest;
import br.com.nexas.appgithubjava.service.RepositorioService;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by user on 04/11/2017.
 */

public class PullRequestsAdapter extends RecyclerView.Adapter<PullRequestsAdapter.ViewHolder> {

    private List<PullRequest> pullRequests;
    private Context context;
    private PullRequestListener pullRequestListener;
    private RepositorioService service;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtPullRequestTitle;
        public TextView txtPullRequestBody;
        //public SmartImageView imgAuthor;
        public CircleImageView imgAuthor;
        public TextView txtAuthorUsername;
        PullRequestListener pullRequestListener;

        public ViewHolder(View itemView, PullRequestListener pullRequestListener) {
            super(itemView);
            txtPullRequestTitle = (TextView) itemView.findViewById(R.id.item_nome_pull_request);
            txtPullRequestBody = (TextView) itemView.findViewById(R.id.item_descricao_pull_request);
            //imgAuthor = (SmartImageView) itemView.findViewById(R.id.pull_request_image);
            imgAuthor = (CircleImageView) itemView.findViewById(R.id.pull_request_image);
            txtAuthorUsername = (TextView) itemView.findViewById(R.id.item_username_pull_request);
            this.pullRequestListener = pullRequestListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
               PullRequest pullRequest = getPullRequest(getAdapterPosition());
               this.pullRequestListener.onPullRequestClick(pullRequest.getPullRequestUrl());
               notifyDataSetChanged();
        }
    }

    public PullRequestsAdapter(Context context, List<PullRequest> pullRequests,
                               PullRequestListener pullRequestListener) {
        this.context = context;
        this.pullRequests = pullRequests;
        this.pullRequestListener = pullRequestListener;
    }

    @Override
    public PullRequestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           Context context = parent.getContext();
           LayoutInflater inflater = LayoutInflater.from(context);

           View pullRequestView = inflater.inflate(R.layout.list_item_pull_request, parent, false);

           ViewHolder viewHolder = new ViewHolder(pullRequestView, this.pullRequestListener);
           return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           PullRequest pullRequest = pullRequests.get(position);
           TextView txtPullRequestTitle = holder.txtPullRequestTitle;
           TextView txtPullRequestBody = holder.txtPullRequestBody;
           //SmartImageView imgAuthor = holder.imgAuthor;
           CircleImageView imgAuthor = holder.imgAuthor;
           TextView txtAuthorUsername = holder.txtAuthorUsername;

           txtPullRequestTitle.setText(pullRequest.getTitle());
           txtPullRequestBody.setText(pullRequest.getBody());
           txtAuthorUsername.setText(pullRequest.getUser().getUsername());
           Picasso.with(context)
                  .load(pullRequest.getUser().getPhoto())
                  .resize(160, 160)
                  .into(imgAuthor);
    }

    public int getItemCount() {
        return pullRequests.size();
    }

    public void atualizaPullRequest(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
        notifyDataSetChanged();
    }

    private PullRequest getPullRequest(int adapterPosition) {
        return pullRequests.get(adapterPosition);
    }

    public interface PullRequestListener {
        void onPullRequestClick(String url);
    }


}
