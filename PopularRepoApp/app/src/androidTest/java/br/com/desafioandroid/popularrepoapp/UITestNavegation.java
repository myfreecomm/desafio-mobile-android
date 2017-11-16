package br.com.desafioandroid.popularrepoapp;

import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Dennys on 15/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class UITestNavegation {

    private UiDevice mDevice;
    private Util util;
    @Before
    public void startMainActivityFromHomeScreen() {

        util = new Util();
        mDevice = util.init();
    }

    @After
    public void afterTest() {
        SystemClock.sleep(2000);
    }

    @Test
    public void navegationWebActivity() throws InterruptedException{

        mDevice.waitForWindowUpdate(null, 4000);
        util.findViewScroll("retrofit");
        mDevice.waitForWindowUpdate(null, 4000);
        util.findViewScroll("Update to Gson 2.8.2.");

    }
}
