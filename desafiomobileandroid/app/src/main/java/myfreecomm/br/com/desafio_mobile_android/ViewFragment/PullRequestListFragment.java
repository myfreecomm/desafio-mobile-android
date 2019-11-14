package myfreecomm.br.com.desafio_mobile_android.ViewFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.southernbox.parallaxrecyclerview.ParallaxRecyclerView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import myfreecomm.br.com.desafio_mobile_android.Adapter.PullRequestAdapter;
import myfreecomm.br.com.desafio_mobile_android.Extendables.MyFragment;
import myfreecomm.br.com.desafio_mobile_android.Model.Pull;
import myfreecomm.br.com.desafio_mobile_android.Presenter.PullRequestListPresenter;
import myfreecomm.br.com.desafio_mobile_android.R;
import myfreecomm.br.com.desafio_mobile_android.ViewActivity.HomeActivity;

public class PullRequestListFragment extends MyFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.txtLoading)
    TextView txtLoading;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.layAvi)
    RelativeLayout layAvi;
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.txtTittle)
    TextView txtTittle;
    @BindView(R.id.rcList)
    ParallaxRecyclerView rcList;

    private PullRequestAdapter adapter;
    private String login, name;
    private PullRequestListPresenter pullRequestListPresenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pull_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pullRequestListPresenter = new PullRequestListPresenter(this);
        configRecyclerView();
        loadData();

    }

    private void configRecyclerView() {

        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        rcList.setHasFixedSize(true);
        rcList.setNestedScrollingEnabled(false);
        rcList.setItemAnimator(new DefaultItemAnimator());
        rcList.setLayoutManager(linearLayout);

    }

    private void loadData() {

        txtTittle.setText(getName());

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            pullRequestListPresenter.loadPullRequest(getLogin(), getName());

        } else {

            dialogNoInternet();

        }
    }


    public void loadDataItems(final List<Pull> pull) {

        if (pull != null) {

            adapter = new PullRequestAdapter(getContext(), pull, PullRequestListFragment.this);
            rcList.setAdapter(adapter);

            if (txtLoading != null) {
                txtLoading.setVisibility(View.INVISIBLE);
            }

            if (layAvi != null) {
                layAvi.setVisibility(View.INVISIBLE);
            }

            if (avi != null) {
                avi.smoothToHide();
            }


        } else {

            txtLoading.setText(R.string.nodata);
            if (avi != null) {
                avi.smoothToHide();
            }
        }

    }

    public void noDataPull() {

        Toast.makeText(getContext(), "Nenhum pull request localizado!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void openBrowser(String html_url) {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(html_url)));

    }

    @OnClick(R.id.btnBack)
    public void onViewClicked() {

        HomeActivity homeactivity = (HomeActivity) getActivity();
        HomeContentFragment frag = new HomeContentFragment();
        FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("pull_request_list");
        ft.setCustomAnimations(R.anim.right_to_left_pull, R.anim.left_to_right_pull);
        ft.replace(R.id.frContent, frag);
        ft.commit();

    }

    private void dialogNoInternet() {

        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.dialog_internet_check);

        RelativeLayout layOk = (RelativeLayout) dialog.findViewById(R.id.layOk);

        layOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();

            }
        });

        dialog.show();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
