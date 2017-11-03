package teste.com.br.teste.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class Util {

    private static ProgressDialog progressDialog;

    public static void showProgressDiaolg(Context ctx, String title, String msg, boolean cancelable) {
        progressDialog = ProgressDialog.show(ctx, title, msg, false, cancelable);
    }

    public static void dimissProgressDialog() {
        progressDialog.dismiss();
    }

    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean mobileIsConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
        boolean wifiIsConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();

        if (!mobileIsConnected && !wifiIsConnected) {
            return false;
        }

        return true;
    }

}
