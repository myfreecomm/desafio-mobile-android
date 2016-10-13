package dev.desafioandroid.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

public class Utils {

    public static void showSimpleDialog(Context context, String message,
                                        OnClickListener clickListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, clickListener)
                .setCancelable(false)
                .show();
    }
}
