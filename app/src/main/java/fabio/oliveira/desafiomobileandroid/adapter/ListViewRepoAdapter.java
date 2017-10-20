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
import fabio.oliveira.desafiomobileandroid.model.Repository;

/**
 * Created by fabio on 17/10/17.
 */

public class ListViewRepoAdapter extends BaseAdapter {

    private Context context;
    private Repository repository;
    private LinearLayout repoListView;

    public ListViewRepoAdapter(Context context, Repository repository){
        this.context = context;
        this.repository = repository;
    }

    @Override
    public int getCount() {
        if (this.repository.getItems() != null) {
            return this.repository.getItems().size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return this.repository.getItems().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            repoListView = (LinearLayout) inflater.inflate(R.layout.repo_list, null, true);
        } else {
            repoListView = (LinearLayout) view;
        }

        TextView txtRepoTitle = (TextView) repoListView.findViewById(R.id.txtPullTitle);
        TextView txtRepoDescription = (TextView) repoListView.findViewById(R.id.txtPullBody);
        TextView txtRepoForks = (TextView) repoListView.findViewById(R.id.txtRepoForks);
        TextView txtRepoStars = (TextView) repoListView.findViewById(R.id.txtRepoStars);
        ImageView imgRepoUserPic = (ImageView) repoListView.findViewById(R.id.imgPullUserPic);
        TextView txtUserName = (TextView) repoListView.findViewById(R.id.txtPullUsername);

        txtRepoTitle.setText(this.repository.getItems().get(i).getName());
        txtRepoDescription.setText(this.repository.getItems().get(i).getDescription());
        txtRepoForks.setText(String.valueOf(this.repository.getItems().get(i).getForks()));
        txtRepoStars.setText(String.valueOf(this.repository.getItems().get(i).getWatchers()));
        Bitmap bitmap = null;
        try {
            bitmap = new ImageLoadTask(this.repository.getItems().get(i).getOwner().getAvatarUrl()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        imgRepoUserPic.setImageBitmap(bitmap);
        txtUserName.setText(this.repository.getItems().get(i).getOwner().getLogin());

        return repoListView;
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
