package testemongeral.com.br.testemongeral.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import testemongeral.com.br.testemongeral.R;
import testemongeral.com.br.testemongeral.model.PullRequest;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder> {

    private Context context;
    private List<PullRequest> pullRequests;

    public PullRequestAdapter(Context context, List<PullRequest> pullRequests) {
        this.context = context;
        this.pullRequests = pullRequests;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pullrequest, parent, false);
        PullRequestAdapter.PullRequestViewHolder holder = new PullRequestAdapter.PullRequestViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PullRequestViewHolder holder, int position) {
        holder.bodyPullrequest.setText(pullRequests.get(position).getBody());
        holder.datePullrequest.setText(pullRequests.get(position).getUpdated_at().split("T")[0]);
        holder.nameOwner.setText(pullRequests.get(position).getUser().getLogin());
        holder.titlePullrequest.setText(pullRequests.get(position).getTitle());
        Picasso.with(context).load(pullRequests.get(position).getUser().getAvatar_url()).placeholder(R.mipmap.ic_launcher_round).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    public class PullRequestViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date_pullrequest)
        TextView datePullrequest;

        @BindView(R.id.title_pullrequest)
        TextView titlePullrequest;

        @BindView(R.id.name_owner)
        TextView nameOwner;

        @BindView(R.id.body_pullrequest)
        TextView bodyPullrequest;

        @BindView(R.id.avatar)
        CircleImageView avatar;

        public PullRequestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
