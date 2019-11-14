package myfreecomm.br.com.desafio_mobile_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import myfreecomm.br.com.desafio_mobile_android.Model.Pull;
import myfreecomm.br.com.desafio_mobile_android.R;
import myfreecomm.br.com.desafio_mobile_android.ViewFragment.PullRequestListFragment;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.MyViewHolder> {

    private Context context;
    private List<Pull> pull;
    private PullRequestListFragment pullRequestListFragment;

    public PullRequestAdapter(Context context, List<Pull> pull, PullRequestListFragment pullRequestListFragment) {
        this.context = context;
        this.pull = pull;
        this.pullRequestListFragment = pullRequestListFragment;
    }

    @Override
    public PullRequestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_prs, parent, false);
        return new PullRequestAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final PullRequestAdapter.MyViewHolder holder, final int position) {

        PullRequestAdapter.MyViewHolder viewHolder = (PullRequestAdapter.MyViewHolder) holder;

        if (pull.get(position).getTitle() != null) {

            holder.txtNameRepo.setText(pull.get(position).getTitle());

        }

        if (pull.get(position).getBody() != null) {

            holder.txtDescRepo.setText(pull.get(position).getBody());

        }

        if (pull.get(position).getUser().getAvatar_url() != null) {

            Glide.with(context)
                    .load(pull.get(position).getUser().getAvatar_url())
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                    .into(holder.imgAvatar);
        }else {

            holder.imgAvatar.setBackgroundDrawable( context.getResources().getDrawable(R.drawable.ic_noavatar) );

        }


        if (pull.get(position).getUser() != null) {

            holder.txtUserName.setText(pull.get(position).getUser().getLogin());
            holder.txtFullName.setText(pull.get(position).getUser().getLogin());

        }


        ((PullRequestAdapter.MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            pullRequestListFragment.openBrowser(pull.get(position).getHtml_url());

            }
        });

    }

    @Override
    public int getItemCount() {
        return pull.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtNameRepo)
        TextView txtNameRepo;

        @BindView(R.id.txtDescRepo)
        TextView txtDescRepo;

        @BindView(R.id.txtUserName)
        TextView txtUserName;

        @BindView(R.id.txtFullName)
        TextView txtFullName;

        @BindView(R.id.imgAvatar)
        CircleImageView imgAvatar;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

