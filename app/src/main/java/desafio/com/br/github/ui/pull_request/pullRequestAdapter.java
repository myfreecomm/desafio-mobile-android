package desafio.com.br.github.ui.pull_request;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.*;

import java.util.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import desafio.com.br.github.*;
import desafio.com.br.github.data.network.model.pull_request.*;


public class pullRequestAdapter extends RecyclerView.Adapter<pullRequestAdapter.ViewHolder>
{
    private ArrayList<PullRequest> data;
    private Context context;
    private static final String TAG = "PullRequestAdapter";

    public pullRequestAdapter(ArrayList<PullRequest> data, Context context)
    {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pull_request, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        PullRequest itemsModel = data.get(i);

        viewHolder.PullRequestName.setText(itemsModel.getTitle());
        viewHolder.pullRequestDescription.setText(itemsModel.getBody());

        if(itemsModel.getUser().getAvatarUrl() != null)
        {
            Picasso.with(viewHolder.view.getContext()).load(itemsModel.getUser().getAvatarUrl()).into(viewHolder.profile_image);
        }

        viewHolder.authorUserName.setText(itemsModel.getUser().getLogin());

        viewHolder.builder(data,context);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ArrayList<PullRequest> data;
        Context context;

        @BindView(R.id.PullRequestName) TextView PullRequestName;
        @BindView(R.id.pullRequestDescription) TextView pullRequestDescription;



        @BindView(R.id.profile_image) ImageView profile_image;
        @BindView(R.id.authorUserName) TextView authorUserName;


        public View view;

        public void builder(ArrayList<PullRequest> data, Context context)
        {
            setData(data);
            setContext(context);
        }

        public void setData(ArrayList<PullRequest> data)
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
