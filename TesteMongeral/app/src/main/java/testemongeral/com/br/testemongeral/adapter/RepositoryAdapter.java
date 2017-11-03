package testemongeral.com.br.testemongeral.adapter;

import android.app.Activity;
import android.os.Bundle;
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
import testemongeral.com.br.testemongeral.fragment.ChangeFragment;
import testemongeral.com.br.testemongeral.fragment.PullRequestsFragment;
import testemongeral.com.br.testemongeral.model.Repository;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositorViewHolder> {

    private Activity context;
    private List<Repository> repositories;

    private RepositoryOnCLickListener repositoryOnCLickListener;
    public OnLoadMoreListener loadMoreListener;

    public RepositoryAdapter(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public RepositoryAdapter(Activity context, List<Repository> repositories) {
        this.context = context;
        this.repositories = repositories;
    }

    @Override
    public RepositorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_repository, parent, false);
        RepositorViewHolder holder = new RepositorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RepositorViewHolder holder, final int position) {
        holder.forks.setText(repositories.get(position).getForks_count());
        holder.nameRepository.setText(repositories.get(position).getName());
        holder.urlClone.setText(repositories.get(position).getClone_url());
        holder.nameOwner.setText(repositories.get(position).getOwner().getLogin());
        holder.stars.setText(repositories.get(position).getStargazers_count());
        Picasso.with(context).load(repositories.get(position).getOwner().getAvatar_url()).placeholder(R.mipmap.ic_launcher_round).into(holder.avatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //repositoryOnCLickListener.onClickRepository(holder.itemView, position);
                Bundle bundle = new Bundle();
                bundle.putString("name", repositories.get(position).getName());
                bundle.putString("owner", repositories.get(position).getOwner().getLogin());
                ChangeFragment.changeFragment(R.id.conatiner, PullRequestsFragment.class, context.getFragmentManager(), false, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public class RepositorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.forks)
        TextView forks;

        @BindView(R.id.name_repository)
        TextView nameRepository;

        @BindView(R.id.name_owner)
        TextView nameOwner;

        @BindView(R.id.stars)
        TextView stars;

        @BindView(R.id.url_clone)
        TextView urlClone;

        @BindView(R.id.avatar)
        CircleImageView avatar;

        public RepositorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface RepositoryOnCLickListener {
        public void onClickRepository(View view, int position);
    }

    public interface OnLoadMoreListener {
        public void onLoadMore(int page, int totalItemsCount);
    }

}
