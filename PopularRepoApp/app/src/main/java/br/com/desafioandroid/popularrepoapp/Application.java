package br.com.desafioandroid.popularrepoapp;

import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by Dennys on 15/11/2017.
 */

public class Application extends android.app.Application {

    public Picasso picassoWithCache;

    @Override
    public void onCreate() {
        super.onCreate();

        File httpCacheDirectory = new File(getCacheDir(), "picasso-cache");
        Cache cache = new Cache(httpCacheDirectory, 15 * 1024 * 1024);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().cache(cache);
        picassoWithCache = new Picasso.Builder(this).downloader(new OkHttp3Downloader(okHttpClientBuilder.build())).build();

    }

    public void loadImageUrl(ImageView imageView, String url){

        if (url != null &&  !url.equals("")) {

            picassoWithCache
                    .with(this)
                    .load(url)
                    .resize(150, 150)
                    .centerCrop()
                    .into(imageView);
        }
    }

    public String formatDate(String value) {

        return value.split("T")[0];
    }
}
