package com.example.tsantana.desafiomobileandroid.activity;

import android.os.Build;

import com.example.tsantana.desafiomobileandroid.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.*;

/**
 * Created by tsantana on 10/12/2017.
 */
//@Config(constants = BuildConfig.class ) // , sdk = Build.VERSION_CODES.LOLLIPOP
@Config(sdk=Build.VERSION_CODES.LOLLIPOP,
        manifest="app/src/main/AndroidManifest.xml")

public class MainActivityTest {
    private MainActivity activity;


    @Test
    public void notNullActivity(){
        assertNotNull(activity);
    }
}
