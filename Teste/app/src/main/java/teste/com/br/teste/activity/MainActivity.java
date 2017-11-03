package teste.com.br.teste.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import teste.com.br.teste.R;
import teste.com.br.teste.fragment.ChangeFragment;
import teste.com.br.teste.fragment.RepositoryFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ChangeFragment.changeFragment(R.id.conatiner, RepositoryFragment.class, getFragmentManager(), false, null);
   }

    public Toolbar getMyToolbar() {
        return myToolbar;
    }

}
