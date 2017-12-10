package com.example.tsantana.desafiomobileandroid.utils;

import android.test.mock.MockContext;


import org.junit.Test;


import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.*;

/**
 * Created by tsantana on 10/12/2017.
 */

public class CommonUtilsTest {

    @Test
    public void testIsNetworkAvailable(){
        assertTrue(CommonUtils.isNetworkAvailable(null) instanceof Boolean);
        assertNotNull( CommonUtils.isNetworkAvailable(new MockContext()));
    }

    @Test
    public void testDataFormatadaPtBr() {
        String retorno = CommonUtils.dataFormatadaPtBr(new Date());
        assertNotNull(retorno);

        assertNotNull(CommonUtils.dataFormatadaPtBr(null));

        Calendar c = Calendar.getInstance();
        c.set(2010,1,8,13,20);

        assertEquals("08/02/2010 13:20", CommonUtils.dataFormatadaPtBr(c.getTime()));

    }
}
