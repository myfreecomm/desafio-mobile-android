package br.com.desafioandroid.popularrepoapp.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import br.com.desafioandroid.popularrepoapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by dennys on 15/11/17.
 */
public class WebActivity extends BaseActivity {

    public final static String URL_PULL = "urlPull";
    @BindView(R.id.web_view) WebView webView;
    @BindView(R.id.progress_bar) public ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String urlPull = getIntent().getStringExtra(URL_PULL);

        if (urlPull != null) {

            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(urlPull);

            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {

                    if (String.valueOf(progress).equals("100")) {

                        progressBar.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
