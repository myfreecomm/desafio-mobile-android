package infnet.edu.br.desafioandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import infnet.edu.br.desafioandroid.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView tv_details_name;
    private TextView tv_details_repository;
    private TextView tv_details_description;
    private TextView tv_details_stars;
    private TextView tv_details_forks;
    private TextView tv_details_url;
    private ImageView img_avatar_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_details_name             = (TextView) findViewById(R.id.tv_details_username);
        tv_details_repository       = (TextView) findViewById(R.id.tv_details_repository_name);
        tv_details_description      = (TextView) findViewById(R.id.tv_details_description);
        tv_details_forks            = (TextView) findViewById(R.id.tv_details_forks);
        tv_details_stars            = (TextView) findViewById(R.id.tv_details_stars);
        tv_details_url              = (TextView) findViewById(R.id.tv_details_url);
        img_avatar_url              = (ImageView) findViewById(R.id.avatar_url);

        String user                 = getIntent().getExtras().getString("username");
        String repo                 = getIntent().getExtras().getString("repository");
        String description          = getIntent().getExtras().getString("description");
        String stars                = getIntent().getExtras().getString("stars");
        String forks                = getIntent().getExtras().getString("forks");
        String url                  = getIntent().getExtras().getString("url");
        String avatar_url           = getIntent().getExtras().getString("avatar_url");

        tv_details_name.setText(user);
        tv_details_repository.setText(repo);
        tv_details_description.setText(description);
        tv_details_stars.setText(stars);
        tv_details_forks.setText(forks);
        tv_details_url.setText(url);
        Picasso.with(getApplicationContext()).load(avatar_url).into(img_avatar_url);

        // setting clickable TextView
        tv_details_url.setMovementMethod(LinkMovementMethod.getInstance());
    } // End on Create

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
