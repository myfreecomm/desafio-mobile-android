package com.app.githubclient;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by thaynan on 24/05/2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}