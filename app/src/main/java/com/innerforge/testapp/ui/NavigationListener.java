package com.innerforge.testapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by LuizH on 03/11/2017.
 */
public interface NavigationListener{
    void navigate(int fragmentId, Bundle bundle, String toolbarName);

    void enableBackButton();
    void disableBackButton();
    void setToolBarTitle(String tile);
}
