package com.example.jorge.myconcrete;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import com.example.jorge.myconcrete.utilite.Utilite;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by jorge on 11/11/2017.
 */

public class TestlistPullRequest {

    public static String mName = "RxJava";
    public static String mLogin = "ReactiveX";



    @Rule
    public ActivityTestRule<DetailActivity> mActivityRule =
            new ActivityTestRule<>(DetailActivity.class, true, false);

    @Test
    public void test(){


        Context targetContext = getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, DetailActivity.class);
        intent.putExtra(Utilite.PUT_EXTRA_NAME, mName);
        intent.putExtra(Utilite.PUT_EXTRA_LOGIN, mLogin);

        mActivityRule.launchActivity(intent);


    }


}

