package myfreecomm.br.com.desafio_mobile_android.ViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import myfreecomm.br.com.desafio_mobile_android.Extendables.MyFragActivity;
import myfreecomm.br.com.desafio_mobile_android.R;

public class SplashActivity extends MyFragActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @BindView(R.id.imgLoad)
    ImageView imgLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Glide.with(this).asGif().load(R.drawable.git_load).into(imgLoad);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
