package br.com.nexas.appgithubjava.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import br.com.nexas.appgithubjava.ListaRepositoriosActivity;
import br.com.nexas.appgithubjava.R;
import br.com.nexas.appgithubjava.adapter.RepositorioAdapter;
import br.com.nexas.appgithubjava.modelo.Repositorio;
import br.com.nexas.appgithubjava.modelo.User;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by user on 04/11/2017.
 */

public class RepositorioDAO extends SQLiteOpenHelper{

    private ImageLoader imageLoader;
    ImageLoaderConfiguration conf;
    Context contextDao;

    public RepositorioDAO(Context context) {
        super(context, "AppGitHubJava", null,1);
        contextDao = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Repositorios (id INT PRIMARY KEY, nome_repo TEXT NOT NULL, desc_repo TEXT, num_stars INT, num_forks INT, owner_repo TEXT, imagem TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insere(Repositorio repositorio){
        // A query SQL INSERT é realizada pelo SQLiteDatabase.
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(repositorio);
        // Metodo chamando comando INSERT.
        db.insert("Repositorios", null, dados);

        Log.i("SQL INSERT", "inseriu" + repositorio.getIdRepositorio());
    }

    public void guardaImagemEmCache(Repositorio repositorio, CircleImageView circleImageView) {
        CircleImageView imageView = circleImageView;
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.person)
                .showImageOnFail(R.drawable.person)
                .showImageOnLoading(R.drawable.person)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        conf = new ImageLoaderConfiguration.Builder(contextDao)
                .defaultDisplayImageOptions(displayImageOptions)
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(2 * 1024 * 1024)
                .threadPoolSize(1)
                .writeDebugLogs()
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(conf);
        imageLoader.displayImage(repositorio.getOwner().getPhoto(),imageView);
        //CircleImageView cimg = new CircleImageView();
    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Repositorio rep) {
        ContentValues dados = new ContentValues();
        dados.put("id",rep.getIdRepositorio());
        dados.put("nome_repo", rep.getNomeRepositorio());
        dados.put("desc_repo", rep.getDescRepositorio());
        dados.put("num_stars", rep.getNumeroEstrelas());
        dados.put("num_forks", rep.getNumeroForks());
        dados.put("owner_repo", rep.getOwner().getUsername());
        dados.put("imagem", rep.getOwner().getPhoto());
        return dados;
    }

    public boolean existe(Repositorio repositorio) {
        SQLiteDatabase db = getReadableDatabase();
        String existe = "SELECT id FROM Repositorios WHERE id = ? LIMIT 1";
        Cursor cursor = db.rawQuery(existe, new String[]{String.valueOf(repositorio.getIdRepositorio())});
        int quantidade = cursor.getCount();
        Log.i("Quantitade ", String.valueOf(quantidade));
        return quantidade > 0;
    }

    public Repositorio selecionaRepositorioDaBase(Repositorio repo) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Repositorios WHERE id = ?", new String[]{String.valueOf(repo.getIdRepositorio())});
        c.moveToNext();
        repo.setIdRepositorio(c.getInt(c.getColumnIndex("id")));
        repo.setNomeRepositorio(c.getString(c.getColumnIndex("nome_repo")));
        repo.setDescRepositorio(c.getString(c.getColumnIndex("desc_repo")));
        repo.setNumeroEstrelas(c.getInt(c.getColumnIndex("num_stars")));
        repo.setNumeroForks(c.getInt(c.getColumnIndex("num_forks")));
        repo.getOwner().setUsername(c.getString(c.getColumnIndex("owner_repo")));
        repo.getOwner().setPhoto(c.getString(c.getColumnIndex("imagem")));
        c.close();
        return repo;
    }

    public List<Repositorio> selecionaTodosOsRepositorios() {
        List<Repositorio> listRep = new ArrayList<Repositorio>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Repositorios",null);
        while(c.moveToNext()){
            User u = new User();
            //String str = "Campo Foto";
            //u.setPhoto(str);
            //u.setName(str);
            //u.setUsername(str);
            Repositorio repo = new Repositorio(0,"name", "description", u,0,0);
            repo.setIdRepositorio(c.getInt(c.getColumnIndex("id")));
            repo.setNomeRepositorio(c.getString(c.getColumnIndex("nome_repo")));
            repo.setDescRepositorio(c.getString(c.getColumnIndex("desc_repo")));
            repo.setNumeroEstrelas(c.getInt(c.getColumnIndex("num_stars")));
            repo.setNumeroForks(c.getInt(c.getColumnIndex("num_forks")));
            Log.i("Seleciona id ", c.getString(c.getColumnIndex("id")));
            Log.i("Seleciona owner ", c.getString(c.getColumnIndex("owner_repo")));
            String username = c.getString(c.getColumnIndex("owner_repo"));
            repo.getOwner().setPhoto(c.getString(c.getColumnIndex("imagem")));
            repo.getOwner().setUsername(username);
            listRep.add(repo);
        }

        c.close();
        return listRep;
    }
}
