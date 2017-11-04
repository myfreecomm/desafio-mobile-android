package infnet.edu.br.desafioandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import infnet.edu.br.desafioandroid.R;
import infnet.edu.br.desafioandroid.activity.DetailsActivity;
import infnet.edu.br.desafioandroid.model.Repository;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.MyViewHolder> {

    private List<Repository> repositories;
    private Context context;

    public RecyclerAdapter(List<Repository> repo, Context c) {
        this.repositories = repo;
        this.context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, context, repositories);
        return myViewHolder;
        // return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Repository repository = repositories.get(position);

        holder.txt_name.setText(repository.getName());
        holder.txt_description.setText(repository.getDescription());
        holder.txt_username.setText(repository.repo_owners.getLogin());
        holder.txt_forks.setText(repository.getForks());
        holder.txt_stars.setText(repository.getStars());

        String image = repository.repo_owners.getAvatar_url();
        Picasso.with(this.context).load(image).into(holder.img_avatar);
    } // End onBindViewHolder

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_name, txt_description, txt_username, txt_forks, txt_stars;
        ImageView img_avatar;

        List<Repository> repositories;
        Context context;
        public MyViewHolder(View itemView, Context c, List<Repository> repo) {
            super(itemView);

            this.repositories = repo;
            this.context = c;
            txt_name = itemView.findViewById(R.id.tv_repo_name);
            txt_description = itemView.findViewById(R.id.tv_repo_description);
            txt_username = itemView.findViewById(R.id.tv_repo_username);
            txt_forks = itemView.findViewById(R.id.tv_repo_forks);
            txt_stars = itemView.findViewById(R.id.tv_repo_stars);
            img_avatar = itemView.findViewById(R.id.img_repo_avatar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Repository repository = this.repositories.get(position);
            Intent intent = new Intent(context, DetailsActivity.class);

            intent.putExtra("username", repository.repo_owners.getLogin());
            intent.putExtra("repository", repository.getName());
            intent.putExtra("description", repository.getDescription());
            intent.putExtra("stars", repository.getStars());
            intent.putExtra("forks", repository.getForks());
            intent.putExtra("url", repository.getUrl());
            intent.putExtra("avatar_url", repository.repo_owners.getAvatar_url());

            this.context.startActivity(intent);

        } // End onClick
    } // End MyViewHolder

}