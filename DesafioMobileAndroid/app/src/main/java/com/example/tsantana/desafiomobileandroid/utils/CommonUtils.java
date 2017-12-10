package com.example.tsantana.desafiomobileandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tsantana on 08/12/2017.
 */

public class CommonUtils {
    public static Boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }catch (Exception e){
            return false;
        }
    }

    public static String dataFormatadaPtBr(Date dataSemFormatacao){
        String dataFormatada ="";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            dataFormatada = formatter.format(dataSemFormatacao);
        }catch (Exception e){
        }finally {
            return dataFormatada;
        }
    }
}
