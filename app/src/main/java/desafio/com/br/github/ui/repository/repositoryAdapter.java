package desafio.com.br.github.ui.repository;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import desafio.com.br.github.*;
import desafio.com.br.github.data.network.model.repository.*;


public class repositoryAdapter extends RecyclerView.Adapter<repositoryAdapter.ViewHolder>
{
    private Repository data;
    private Context context;
    private static final String TAG = "RepositoryAdapter";

    public repositoryAdapter(Repository data, Context context)
    {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repository, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {


        Item itemsModel = data.getItems().get(i);

        viewHolder.repositoryName.setText(itemsModel.getName());
        viewHolder.repositoryDescription.setText(itemsModel.getDescription());
        viewHolder.repositoryForks.setText(itemsModel.getForksCount().toString());
        viewHolder.repositoryStars.setText(itemsModel.getStargazersCount().toString());



        if(itemsModel.getOwner().getAvatarUrl() != null)
        {
            Picasso.with(viewHolder.view.getContext()).load(itemsModel.getOwner().getAvatarUrl()).into(viewHolder.profile_image);
        }

        viewHolder.authorUserName.setText(itemsModel.getOwner().getLogin());
        //viewHolder.authorCompleteName.setText(itemsModel.getOwner());

        viewHolder.builder(data,context);

    }


    @Override
    public int getItemCount() {
        return data.getItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Repository data;
        Context context;

        @BindView(R.id.repositoryName) TextView repositoryName;
        @BindView(R.id.repositoryDescription) TextView repositoryDescription;
        @BindView(R.id.repositoryForks) TextView repositoryForks;
        @BindView(R.id.repositoryStars) TextView repositoryStars;


        @BindView(R.id.profile_image) ImageView profile_image;
        @BindView(R.id.authorUserName) TextView authorUserName;
        //@BindView(R.id.authorCompleteName) TextView authorCompleteName;


        public View view;

        public void builder(Repository data,Context context)
        {
            setData(data);
            setContext(context);
        }

        public void setData(Repository data)
        {
            this.data = data;
        }

        public void setContext(Context context)
        {
            this.context = context;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Log.d(TAG,"onClick"+Log.d(TAG,"onClick "));
                }
            });

        }
    }
}
