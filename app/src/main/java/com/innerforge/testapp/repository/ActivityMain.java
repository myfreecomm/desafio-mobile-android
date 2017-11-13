package com.innerforge.testapp.repository;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.innerforge.testapp.repository.detail.FragmentRepositoryDetail;
import com.innerforge.testapp.repository.list.FragmentRepositoryList;
import com.innerforge.testapp.ui.NavigationListener;
import com.innerforge.testapp.R;

public class ActivityMain extends AppCompatActivity implements NavigationListener {

    public static final int FRAGMENT_REPOSITORY_LIST_ID = 0;
    public static final int FRAGMENT_REPOSITORY_DETAIL_ID = 1;

    private Toolbar mActionBarToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testapp_activity_main);
        mActionBarToolbar = findViewById(R.id.TestApp_ActivityMain_toolbar);
        setSupportActionBar(mActionBarToolbar);
        if(savedInstanceState == null) {
            goToFragment(new FragmentRepositoryList(), null, true);
        }
    }

    public void goToFragment(Fragment fragment, Bundle bundle, boolean add) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        //fragmentTransaction.setCustomAnimations(R.anim.alpha_enter_anim, R.anim.alpha_exit_anim, R.anim.alpha_enter_anim, R.anim.alpha_exit_anim);

        if(add) {
            fragmentTransaction.add(R.id.TestApp_ActivityMain_content, fragment);
        }
        else {
            fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.enter_back_anim, R.anim.exit_back_anim);
            fragmentTransaction.replace(R.id.TestApp_ActivityMain_content, fragment);
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void navigate(int fragmentId, Bundle bundle, String toolbarName) {
        switch (fragmentId){
            case FRAGMENT_REPOSITORY_LIST_ID:
                break;
            case FRAGMENT_REPOSITORY_DETAIL_ID:
                goToFragment(new FragmentRepositoryDetail(), bundle, false);
                break;
        }
    }

    @Override
    public void enableBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
    }

    @Override
    public void disableBackButton() {
        //mActionBarToolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    public void setToolBarTitle(String title) {
        mActionBarToolbar.setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
