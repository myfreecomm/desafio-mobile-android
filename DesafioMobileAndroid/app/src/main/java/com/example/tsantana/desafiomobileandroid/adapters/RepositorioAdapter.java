package com.example.tsantana.desafiomobileandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tsantana.desafiomobileandroid.R;
import com.example.tsantana.desafiomobileandroid.activity.SecondActivity;
import com.example.tsantana.desafiomobileandroid.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.tsantana.desafiomobileandroid.data.model.Repositorio;

/**
 * Created by tsantana on 07/12/2017.
 */

public class RepositorioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private final int VIEW_ITEM_TYPE = 1;
    private final int VIEW_PROG_TYPE = 0;

    private ArrayList<Repositorio> mRepositoriosList;
    private Context context;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isMoreLoading = true;
    private int currentPage = 1;

    public void setMore(boolean isMore) {
        this.isMoreLoading = isMore;
    }

    public interface OnLoadMoreListener{
        void onLoadMore(int page);
    }

    public RepositorioAdapter(Context context) {
        this.context = context;
        this.onLoadMoreListener = (OnLoadMoreListener) context;
        this.mRepositoriosList = new ArrayList<>();

    }

    public void addAll(ArrayList<Repositorio> repositorios){
        mRepositoriosList.clear();
        mRepositoriosList.addAll(repositorios);
        notifyDataSetChanged();
    }

    public void addItemMore(ArrayList<Repositorio> repositorios){
        int sizeInit = mRepositoriosList.size();
        mRepositoriosList.addAll(repositorios);
        notifyItemRangeChanged(sizeInit, mRepositoriosList.size());
    }

    public void showLoading() {
        if (isMoreLoading && mRepositoriosList != null && this.onLoadMoreListener != null) {
            isMoreLoading = false;
            currentPage++;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    //Acrescenta um item null na listagem para dar espaço ao ProgressBar
                    // o notify atualiza o adapter na ultima posição do recycleview
                    mRepositoriosList.add(null);
                    notifyItemInserted(mRepositoriosList.size() - 1);
                    onLoadMoreListener.onLoadMore(currentPage);
                }
            });
        }
    }

    //remove o ProgressBar
    // removendo o item nulo que tinha sito adicionado no shoLoading()
    public void dismissLoading() {
        if (mRepositoriosList != null && mRepositoriosList.size() > 0) {
            mRepositoriosList.remove(mRepositoriosList.size() - 1);
            notifyItemRemoved(mRepositoriosList.size());
        }
    }

    //Se o get da lista retorna nulo vai retornar 0
    //para ser utilizado o ProgressBarViewHolder
    // se não, será utilizado o RepositorioViewHolder
    @Override
    public int getItemViewType(int position) {
        return mRepositoriosList.get(position) !=null ?VIEW_ITEM_TYPE: VIEW_PROG_TYPE;
    }

    //Infla o adapter com o item de acordo com o getItemViewType
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        if(viewType == VIEW_ITEM_TYPE ) {
            return new RepositorioViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_main, parent, false));
        }else{
            return new ProgressBarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_main_progress, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if( holder instanceof RepositorioViewHolder) {
           Repositorio repositorio = mRepositoriosList.get(position);

            if (repositorio == null){
                String teste ="";
            }


           ((RepositorioViewHolder)holder).tvNomeRepo.setText(repositorio.getNome());
           ((RepositorioViewHolder)holder).tvDescricaoRepo.setText(repositorio.getDescricao());
           ((RepositorioViewHolder)holder).tvForks.setText(repositorio.getForks().toString());
           ((RepositorioViewHolder)holder).tvStars.setText(repositorio.getStars().toString());
           ((RepositorioViewHolder)holder).tvUserName.setText(repositorio.getOwner().getLogin());

           //foto
           Picasso.with(this.context)
                   .load(repositorio.getOwner().getFoto())
                   .into(((RepositorioViewHolder)holder).ivFoto);

       }
    }

    @Override
    public int getItemCount() {
        return mRepositoriosList.size();
    }

    protected static class RepositorioViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvNomeRepo;
        protected TextView tvDescricaoRepo;
        protected TextView tvForks;
        protected TextView tvStars;
        protected TextView tvUserName;
        protected CircleImageView ivFoto;
        protected ProgressBar progressBar;

        public RepositorioViewHolder(final View itemView) {
            super(itemView);
            tvNomeRepo = (TextView) itemView.findViewById(R.id.nomeRepo);
            tvDescricaoRepo = (TextView) itemView.findViewById(R.id.descricaoRepo);
            tvForks = (TextView) itemView.findViewById(R.id.forks);
            tvStars = (TextView) itemView.findViewById(R.id.stars);
            tvUserName = (TextView) itemView.findViewById(R.id.userName);
            ivFoto = (CircleImageView) itemView.findViewById(R.id.foto);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pogressBar);

            //evento de click para tela de Pull Requests
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (CommonUtils.isNetworkAvailable(itemView.getContext())) {
                        Intent intent = new Intent(itemView.getContext(), SecondActivity.class);

                        intent.putExtra("ownerRepo", tvUserName.getText().toString());
                        intent.putExtra("repoName", tvNomeRepo.getText().toString());
                        itemView.getContext().startActivity(intent);
                    }
                }
            });

        }
    }

    protected static class ProgressBarViewHolder extends RecyclerView.ViewHolder{
        protected ProgressBar progressBar;

        public ProgressBarViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pogressBar);
        }
    }
}
