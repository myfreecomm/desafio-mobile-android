package br.com.andreozawa.githubjavapop.asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.andreozawa.githubjavapop.R;
import br.com.andreozawa.githubjavapop.model.enums.Method;

/**
 * Created by andre.ozawa on 30/10/2017.
 */

public class PublicReposAsyncTask extends AsyncTask<Object, String, String> {

    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECT_TIMEOUT = 30000;
    private Context context;

    private OnPublicReposListener onPublicReposListener;

    public interface OnPublicReposListener {
        void onPublicReposCallback(String resultJson);
    }

    public PublicReposAsyncTask(Context context, OnPublicReposListener onPublicReposListener) {
        this.context = context;
        this.onPublicReposListener = onPublicReposListener;
    }

    @Override
    protected String doInBackground(Object... params) {
        String result = null;

        try {
            HttpURLConnection httpURLConnection = this.getHttpURLConnection(params);

            httpURLConnection.connect();

            result = this.getJsonResult(httpURLConnection);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private HttpURLConnection getHttpURLConnection(Object... params) throws IOException {
        Method method = (Method) params[0];

        URL url = new URL(this.getUrl(params));

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestProperty("Content-type", "application/json");
        httpURLConnection.setRequestMethod(method.getDescription());
        httpURLConnection.setReadTimeout(READ_TIMEOUT);
        httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
        httpURLConnection.setDoInput(true);

        if (params[0] != Method.GET) {
            httpURLConnection.setDoOutput(true);
        }

        return httpURLConnection;
    }

    private String getUrl(Object... params) {
        return String.valueOf(params[1]);
    }

    protected String getJsonResult(HttpURLConnection httpURLConnection) throws IOException {
        return this.readInputStream(httpURLConnection.getInputStream());
    }

    private String readInputStream(InputStream inputStream) throws IOException {
        String inputLine = "";

        InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");

        BufferedReader reader = new BufferedReader(streamReader);

        StringBuilder stringBuilder = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        reader.close();
        streamReader.close();

        return stringBuilder.toString();
    }

//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//
//        this.progressDialog = ProgressDialog.show(this.context, context.getString(R.string.start_proccess), context.getString(R.string.wait_proccess));
//    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

//        this.progressDialog.dismiss();

        this.onPublicReposListener.onPublicReposCallback(s);
    }
}
