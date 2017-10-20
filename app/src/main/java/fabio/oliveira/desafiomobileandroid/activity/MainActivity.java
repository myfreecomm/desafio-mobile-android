package fabio.oliveira.desafiomobileandroid.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import fabio.oliveira.desafiomobileandroid.R;
import fabio.oliveira.desafiomobileandroid.adapter.ListViewRepoAdapter;
import fabio.oliveira.desafiomobileandroid.model.Item;
import fabio.oliveira.desafiomobileandroid.model.Pull;
import fabio.oliveira.desafiomobileandroid.model.Repository;
import fabio.oliveira.desafiomobileandroid.viewmodel.PullViewModel;
import fabio.oliveira.desafiomobileandroid.viewmodel.RepositoryViewModel;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private int currentPage = 1;

    private ProgressDialog progressDialog;
    private ListView listViewRepo;
    private Repository repository;

    private Button btnPreviousPage;
    private TextView txtCurrentPage;
    private Button btnNextPage;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initRealm();
        this.initComponents();
        this.initFirstScreen();
        this.initEvents();
    }

    private Context getContext(){
        return this.getApplicationContext();
    }

    private void initRealm(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("desafio.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void initComponents(){
        this.listViewRepo = (ListView) findViewById(R.id.listViewRepo);
        this.btnPreviousPage = (Button) findViewById(R.id.btnPreviousPage);
        this.txtCurrentPage = (TextView) findViewById(R.id.txtCurrentPage);
        this.btnNextPage = (Button) findViewById(R.id.btnNextPage);
    }

    private void initFirstScreen(){
        this.changePage(currentPage);
    }

    private void initEvents(){
        this.btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage > 1) {
                    changePage(currentPage-1);
                }
            }
        });

        this.btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(currentPage+1);
            }
        });
        this.listViewRepo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int FIRST_PAGE = 1;

                Item item = (Item) adapterView.getItemAtPosition(i);
                if(intent == null) {
                    intent = new Intent(getContext(), DetailActivity.class);
                }
                intent.putExtra("url", item.getPullsUrl());
                goItemPull(item);
            }
        });
    }

    private void changePage(final int page) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Carregando...");
                progressDialog.setIndeterminate(true);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                repository = new RepositoryViewModel().getResult(page);
                currentPage = page;
                return null;
            }

            @Override
            protected void onPostExecute(Void value) {
                listViewRepo.setAdapter(new ListViewRepoAdapter(getApplicationContext(), repository));
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtCurrentPage.setText(String.valueOf(currentPage));
                        progressDialog.dismiss();
                    }
                }, 2000);
            }
        }.execute();
    }

    private void goItemPull(final Item item){
        new AsyncTask<Void, Void, Void>() {
            Pull[] pull = null;
            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Carregando...");
                progressDialog.setIndeterminate(true);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                pull = new PullViewModel().getResult(item);
                intent.putExtra("pull", pull);
                startActivity(intent);
                return null;
            }

            @Override
            protected void onPostExecute(Void value) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 1000);
            }
        }.execute();
    }
}
