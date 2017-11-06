package myfreecomm.br.com.desafio_mobile_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import myfreecomm.br.com.desafio_mobile_android.Model.Items;
import myfreecomm.br.com.desafio_mobile_android.R;
import myfreecomm.br.com.desafio_mobile_android.ViewFragment.HomeContentFragment;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context context;
    private List<Items> items;
    private HomeContentFragment homeContentFragment;

    public HomeAdapter(Context context, List<Items> items, HomeContentFragment homeContentFragment) {

        this.context = context;
        this.items = items;
        this.homeContentFragment = homeContentFragment;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        MyViewHolder viewHolder = (MyViewHolder) holder;

        if(items.get(position).getName() != null){
            holder.txtNameRepo.setText(items.get(position).getName());
        }

        if(items.get(position).getDescription() != null){
            holder.txtDescRepo.setText(items.get(position).getDescription());
        }

        holder.txtOne.setText(String.valueOf(items.get(position).getForks()));
        holder.txtTwo.setText(String.valueOf(items.get(position).getStargazers_count()));

        if(items.get(position).getFull_name() != null){
            holder.txtFullName.setText(items.get(position).getFull_name());
        }

        if(items.get(position).getOwner().getLogin() != null){
            holder.txtUserName.setText(items.get(position).getOwner().getLogin());
        }

        if(items.get(position).getOwner().getAvatar_url() != null){

            Glide.with(context)
                 .load(items.get(position).getOwner().getAvatar_url())
                 .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                 .into(holder.imgAvatar);

        }else {

            holder.imgAvatar.setBackgroundDrawable( context.getResources().getDrawable(R.drawable.ic_noavatar) );

        }

        ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(items.get(position).getOwner().getLogin() != null && items.get(position).getName() != null){

                    homeContentFragment.loadPullRequest(
                            items.get(position).getOwner().getLogin(),
                            items.get(position).getName()

                    );


                }else {

                    homeContentFragment.loadPullRequestError();

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swap(List<Items> itemsUpdate){
        items.addAll(itemsUpdate);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtNameRepo)
        TextView txtNameRepo;

        @BindView(R.id.txtDescRepo)
        TextView txtDescRepo;

        @BindView(R.id.txtOne)
        TextView txtOne;

        @BindView(R.id.txtTwo)
        TextView txtTwo;

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
