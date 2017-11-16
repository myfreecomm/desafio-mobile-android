package br.com.desafioandroid.popularrepoapp;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.desafioandroid.popularrepoapp.presenter.HomePresenterImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dennys on 15/11/2017.
 */


@RunWith(AndroidJUnit4.class)
public class UnitTestValidRequest {

    private HomePresenterImpl homePresenter;
    private UiDevice mDevice;

    @Before
    public void init(){

        mDevice = new Util().init();
        homePresenter = new HomePresenterImpl();
        homePresenter.findRepositoryByPage(1);
    }

    @Test
    public void validAccessApi() throws Exception {

        mDevice.waitForWindowUpdate(null, 4000);
        assertEquals(homePresenter.MAX_ITEMS_PER_REQUEST, homePresenter.listItenItemRepositoryEntities.size());
    }

}
