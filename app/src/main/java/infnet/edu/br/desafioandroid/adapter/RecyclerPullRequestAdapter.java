package infnet.edu.br.desafioandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import infnet.edu.br.desafioandroid.R;
import infnet.edu.br.desafioandroid.model.PullRequest;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class RecyclerPullRequestAdapter extends RecyclerView.Adapter <RecyclerPullRequestAdapter.MyViewHolder> {

    private List<PullRequest> pullRequests;
    private Context context;

    public RecyclerPullRequestAdapter(List<PullRequest> pullrequest, Context c) {
        this.pullRequests = pullrequest;
        this.context = c;
    }

    @Override
    public RecyclerPullRequestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_pull_request, parent, false);
        RecyclerPullRequestAdapter.MyViewHolder myViewHolder = new RecyclerPullRequestAdapter.MyViewHolder(view, context, pullRequests);
        return myViewHolder;
        // return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerPullRequestAdapter.MyViewHolder holder, int position) {

        PullRequest pullRequest = pullRequests.get(position);

        holder.txt_name.setText(pullRequest.user.getLogin());
        //holder.txt_description.setText(pullRequest.user.getDescription());
        String image = pullRequest.user.getAvatarUrl();
        Picasso.with(this.context).load(image).into(holder.img_avatar);
    } // End onBindViewHolder

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView txt_name, txt_description, txt_username, txt_forks, txt_stars;
        ImageView img_avatar;

        List<PullRequest> pullRequests;
        Context context;
        public MyViewHolder(View itemView, Context c, List<PullRequest> pullRequests) {
            super(itemView);

            this.pullRequests = pullRequests;
            this.context = c;
            txt_name = itemView.findViewById(R.id.tv_pull_request_name);
            txt_description = itemView.findViewById(R.id.tv_pull_request_description);
            txt_username = itemView.findViewById(R.id.tv_pull_request_username);
            img_avatar = itemView.findViewById(R.id.img_pull_request_avatar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {



        } // End onClick
    } // End MyViewHolder

}
