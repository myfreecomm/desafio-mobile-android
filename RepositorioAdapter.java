package br.com.nexas.appgithubjava.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.nexas.appgithubjava.R;
import br.com.nexas.appgithubjava.dao.RepositorioDAO;
import br.com.nexas.appgithubjava.modelo.Repositorio;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by user on 02/11/2017.
 */

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.ViewHolder> {

    private List<Repositorio> repositorios;
    private Context context;
    private RepositorioListener repositorioListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtRepoName;
        public TextView txtRepoDescription;
        public TextView txtForksCount;
        public TextView txtStarsCount;
        public TextView txtAuthorUsername;
        //public SmartImageView imgAuthorPicture;
        public CircleImageView imgAuthorPicture;
        RepositorioListener repositorioListener;


        public ViewHolder(View itemView, RepositorioListener repositorioListener) {
            super(itemView);
            txtRepoName = (TextView) itemView.findViewById(R.id.item_nome_repositorio);
            txtRepoDescription = (TextView) itemView.findViewById(R.id.item_descricao);
            txtForksCount = (TextView) itemView.findViewById(R.id.item_forks);
            txtStarsCount = (TextView) itemView.findViewById(R.id.item_stars);
            txtAuthorUsername = (TextView) itemView.findViewById(R.id.item_username);
            //imgAuthorPicture = (SmartImageView) itemView.findViewById(R.id.repositorio_image);
            imgAuthorPicture = (CircleImageView) itemView.findViewById(R.id.repositorio_image);
            this.repositorioListener = repositorioListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Repositorio rep = getRepositorio(getAdapterPosition());
            this.repositorioListener.onRepositoryClick( rep.getOwner().getUsername(),rep.getNomeRepositorio());
            notifyDataSetChanged();
        }
    }

    public RepositorioAdapter(Context context, List<Repositorio> repositorios, RepositorioListener repLis) {
        this.context = context;
        this.repositorios = repositorios;
        this.repositorioListener = repLis;
    }

    @Override
    public RepositorioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repositorioView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(repositorioView, this.repositorioListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
                RepositorioDAO dao = new RepositorioDAO(this.context);
                final Repositorio repositorio = repositorios.get(position);
                TextView txtRepoName = holder.txtRepoName;
                TextView txtRepoDescription = holder.txtRepoDescription;
                TextView txtForksCount = holder.txtForksCount;
                TextView txtStarsCount = holder.txtStarsCount;
                TextView txtAuthorUsername = holder.txtAuthorUsername;
                //SmartImageView imgAuthorPicture = holder.imgAuthorPicture;
                CircleImageView imgAuthorPicture = holder.imgAuthorPicture;

                txtRepoName.setText(repositorio.getNomeRepositorio());
                txtRepoDescription.setText(repositorio.getDescRepositorio());
                txtForksCount.setText(String.valueOf(repositorio.getNumeroForks()));
                txtStarsCount.setText(String.valueOf(repositorio.getNumeroEstrelas()));
                txtAuthorUsername.setText(repositorio.getOwner().getUsername());

                Picasso.with(context)
                        .load(repositorio.getOwner().getPhoto())
                        .resize(160, 160)
                        .into(imgAuthorPicture);
                dao.guardaImagemEmCache(repositorio,imgAuthorPicture);
    }

    @Override
    public int getItemCount() {
        return repositorios == null ? 0 : repositorios.size();
    }


    public Repositorio getRepositorio(int adapterPosition) {
          return repositorios.get(adapterPosition);
    }


    public interface RepositorioListener {
        void onRepositoryClick(String authorName, String repoName);
    }

    public void atualizaRepositorios(List<Repositorio> repositorios) {
        this.repositorios = repositorios;
        notifyDataSetChanged();
    }


    public void add(Repositorio repo) {
         repositorios.add(repo);
         notifyItemInserted(repositorios.size() - 1);
    }

    public void addAll(List<Repositorio> repositories) {
        RepositorioDAO dao = new RepositorioDAO(this.context);
        for (Repositorio repo : repositories) {
            //add(repo);
            if(!dao.existe(repo)){
                add(repo);
                dao.insere(repo);
            }else{
                add(dao.selecionaRepositorioDaBase(repo));

            }

        }
        dao.close();
    }

    public List<Repositorio> getRepositories() {
        return repositorios;
    }


//    @Override
//    public int getCount() {
//        return repositorios.size();
//    }

//    @Override
//    public Object getItem(int position) {
//        return repositorios.get(position);
//    }

//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Repositorio repositorio = repositorios.get(position);

//        View view = convertView;
//        LayoutInflater inflater = LayoutInflater.from(context);
//        if(view == null){
//            view = inflater.inflate(R.layout.list_item, parent, false);
//        }

//        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
//        campoNome.setText(repositorio.getNomeSobrenome());

//        TextView campoNomeRepositorio = (TextView) view.findViewById(R.id.item_nome_repositorio);
//        campoNomeRepositorio.setText(repositorio.getNomeRepositorio());

//        TextView campoDescricao = (TextView) view.findViewById(R.id.item_descricao);
//        campoDescricao.setText(repositorio.getDescRepositorio());

//        TextView campoUsername = (TextView) view.findViewById(R.id.item_username);
//        campoUsername.setText(repositorio.getUsername());

//        TextView campoForks = (TextView) view.findViewById(R.id.item_forks);
//        campoForks.setText(repositorio.getNumeroForks());

//        TextView campoStars = (TextView) view.findViewById(R.id.item_stars);
//        campoStars.setText(String.valueOf(repositorio.getNumeroEstrelas()));

//        SmartImageView campoFoto = (SmartImageView) view.findViewById(R.id.repositorio_image);
//        Bitmap bitmap = BitmapFactory.decodeFile(repositorio.getImagemRepositorio());
//        campoFoto.setImageBitmap(bitmap);
//        campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
//        campoFoto.setImageUrl(repositorio.getImagemRepositorio());

//        return view;
//    }

}
