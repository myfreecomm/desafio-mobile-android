package com.app.githubclient.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by thaynan on 25/05/2016.
 */
public class SaveSharedPreference {

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setResponse(Context ctx, String value)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("response", value);
        editor.commit();
    }
    public static String getResponse(Context ctx)
    {
        return getSharedPreferences(ctx).getString("response", "");
    }

}
