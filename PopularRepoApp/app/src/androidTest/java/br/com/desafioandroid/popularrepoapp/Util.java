package br.com.desafioandroid.popularrepoapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.fail;

/**
 * Created by Dennys on 13/11/2017.
 */

public class Util {

    private static final String BASIC_SAMPLE_PACKAGE = "br.com.desafioandroid.popularrepoapp";
    private static final int LAUNCH_TIMEOUT = 2000;
    private UiDevice mDevice;
    Context context;

    public UiDevice init(){

        mDevice = UiDevice.getInstance(getInstrumentation());
        mDevice.pressHome();

        final String launcherPackage = mDevice.getLauncherPackageName();
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);


        return mDevice;
    }

    public void findViewScroll(String txtName){

        boolean settingsFound = false;
        UiScrollable textScroll;
        UiObject text;
        while (!settingsFound) {

            try {
                textScroll = new UiScrollable(new UiSelector().scrollable(true));
                textScroll.scrollIntoView(new UiSelector().text(txtName));
                text = new UiObject(new UiSelector().text(txtName));
                settingsFound = true;
                text.click();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public UiObject2 waitForObject(BySelector selector, UiDevice mDevice) throws InterruptedException {

        UiObject2 object = null;
        int timeout = 30000;
        int delay = 1000;
        long time = System.currentTimeMillis();
        while (object == null) {

            object = mDevice.findObject(selector);
            Thread.sleep(delay);
            if (System.currentTimeMillis() - timeout > time) {
                fail();
            }
        }
        return object;
    }
}
