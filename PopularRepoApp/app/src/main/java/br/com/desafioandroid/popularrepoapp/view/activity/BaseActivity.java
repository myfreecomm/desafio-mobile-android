package br.com.desafioandroid.popularrepoapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.desafioandroid.popularrepoapp.Application;
import br.com.desafioandroid.popularrepoapp.R;

/**
 * Created by Dennys on 15/11/2017.
 */

public class BaseActivity extends AppCompatActivity {

    public Application application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = ((Application) getApplication());
    }

    public void showMessage(){

        Toast.makeText(this, getString(R.string.message_erro), Toast.LENGTH_LONG).show();
    }
}
