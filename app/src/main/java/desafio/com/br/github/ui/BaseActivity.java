package desafio.com.br.github.ui;

import android.app.*;
import android.support.annotation.*;

import butterknife.*;

/**
 * Created by rafael on 25/01/18.
 */

public abstract class BaseActivity extends Activity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


}