package com.example.jorge.myconcrete.utilite;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jorge on 10/11/2017.
 */


/**
 * common for all project.
 */
public class Common {

    /**
     * checks if internet is ok .
     */
    public static boolean isOnline(Object object) {
        ConnectivityManager cm =
                (ConnectivityManager) object;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
