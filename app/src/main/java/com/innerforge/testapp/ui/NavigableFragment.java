package com.innerforge.testapp.ui;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by LuizH on 03/11/2017.
 */

public class NavigableFragment extends Fragment{

    private NavigationListener navigationListener;

    public NavigationListener getNavigationListener() {
        return navigationListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationListener = (NavigationListener) context;
    }

}
