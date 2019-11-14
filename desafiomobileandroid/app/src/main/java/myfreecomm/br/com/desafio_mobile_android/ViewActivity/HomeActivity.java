package myfreecomm.br.com.desafio_mobile_android.ViewActivity;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;

import myfreecomm.br.com.desafio_mobile_android.Extendables.MyFragActivity;
import myfreecomm.br.com.desafio_mobile_android.R;
import myfreecomm.br.com.desafio_mobile_android.ViewFragment.HomeContentFragment;

public class HomeActivity extends MyFragActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeContentFragment HomeContent = new HomeContentFragment();
        getMyFragTransaction().add(R.id.frContent, HomeContent);
        getMyFragTransaction().commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
