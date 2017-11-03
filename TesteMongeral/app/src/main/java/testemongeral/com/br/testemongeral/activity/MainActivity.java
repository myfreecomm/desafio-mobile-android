package testemongeral.com.br.testemongeral.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import testemongeral.com.br.testemongeral.R;
import testemongeral.com.br.testemongeral.fragment.ChangeFragment;
import testemongeral.com.br.testemongeral.fragment.RepositoryFragment;

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
