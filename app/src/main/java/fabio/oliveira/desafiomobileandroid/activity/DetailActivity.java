package fabio.oliveira.desafiomobileandroid.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import fabio.oliveira.desafiomobileandroid.R;
import fabio.oliveira.desafiomobileandroid.adapter.ListViewPullAdapter;
import fabio.oliveira.desafiomobileandroid.model.Pull;

public class DetailActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private ListView listViewPull;
    private Pull[] pull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.initComponents();
        this.initFirstScreen();
    }

    private void initComponents(){
        this.listViewPull = (ListView) findViewById(R.id.listViewPull);
    }

    private void initPull(){
        this.pull = (Pull[]) this.getIntent().getExtras().get("pull");
    }

    private void initListView(){
        listViewPull.setAdapter(new ListViewPullAdapter(this.getApplicationContext(), this.pull));
    }

    private void initFirstScreen(){
        new AsyncTask<Void, Void, Void>() {
            Pull[] pull = null;
            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(DetailActivity.this);
                progressDialog.setMessage("Carregando...");
                progressDialog.setIndeterminate(true);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                initPull();
                initListView();
                return null;
            }

            @Override
            protected void onPostExecute(Void value) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 2000);
            }
        }.execute();
    }
}
