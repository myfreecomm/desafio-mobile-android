package fabio.oliveira.desafiomobileandroid.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import fabio.oliveira.desafiomobileandroid.R;
import fabio.oliveira.desafiomobileandroid.model.Pull;

/**
 * Created by fabio on 17/10/17.
 */

public class ListViewPullAdapter extends BaseAdapter {

    private Context context;
    private Pull[] pull;
    private LinearLayout pullListView;

    public ListViewPullAdapter(Context context, Pull[] pull){
        this.context = context;
        this.pull = pull;
    }

    @Override
    public int getCount() {
        if (this.pull != null) {
            return pull.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            pullListView = (LinearLayout) inflater.inflate(R.layout.pull_list, null, true);
        } else {
            pullListView = (LinearLayout) view;
        }

        TextView txtPullTitle = (TextView) pullListView.findViewById(R.id.txtPullTitle);
        TextView txtPullBody = (TextView) pullListView.findViewById(R.id.txtPullBody);
        TextView txtPullData = (TextView) pullListView.findViewById(R.id.txtPullData);
        ImageView imgPullUserPic = (ImageView) pullListView.findViewById(R.id.imgPullUserPic);
        TextView txtPullName = (TextView) pullListView.findViewById(R.id.txtPullUsername);

        txtPullTitle.setText(this.pull[i].getTitle());
        txtPullBody.setText(this.pull[i].getBody());
        txtPullData.setText(this.pull[i].getData());
        Bitmap bitmap = null;
        try {
            bitmap = new ImageLoadTask(this.pull[i].getUser().getAvatarUrl()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        imgPullUserPic.setImageBitmap(bitmap);
        txtPullName.setText(this.pull[i].getUser().getLogin());

        return pullListView;
    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;

        public ImageLoadTask(String url) {
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
        }

    }
}
