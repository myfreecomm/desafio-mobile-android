package myfreecomm.br.com.desafio_mobile_android.Extendables;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;

public class MyFragActivity extends FragmentActivity {

    private FragmentTransaction myFragTransaction = null;
    public Point dispSize = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );

        myFragTransaction = getSupportFragmentManager().beginTransaction();

        Display display = getWindowManager().getDefaultDisplay();
        dispSize = new Point();
        display.getSize(dispSize);
    }

    public FragmentTransaction getMyFragTransaction() {
        return myFragTransaction;
    }

}
