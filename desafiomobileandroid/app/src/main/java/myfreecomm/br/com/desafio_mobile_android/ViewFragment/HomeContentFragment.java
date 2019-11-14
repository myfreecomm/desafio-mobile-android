package myfreecomm.br.com.desafio_mobile_android.ViewFragment;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import myfreecomm.br.com.desafio_mobile_android.Adapter.HomeAdapter;
import myfreecomm.br.com.desafio_mobile_android.Extendables.MyFragment;
import myfreecomm.br.com.desafio_mobile_android.Model.Items;
import myfreecomm.br.com.desafio_mobile_android.Presenter.HomeContentPresenter;
import myfreecomm.br.com.desafio_mobile_android.R;
import myfreecomm.br.com.desafio_mobile_android.Util.PaginationScroll;
import myfreecomm.br.com.desafio_mobile_android.ViewActivity.HomeActivity;

public class HomeContentFragment extends MyFragment {

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.txtLoading)
    TextView txtLoading;
    Unbinder unbinder;
    @BindView(R.id.layAvi)
    RelativeLayout layAvi;
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.rcList)
    ParallaxRecyclerView rcList;

    private HomeAdapter adapter;
    private HomeContentPresenter homeContentPresenter;

    private int pageNumber = 1;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeContentPresenter = new HomeContentPresenter(this);
        configRecyclerView();
        loadData();

    }

    private void configRecyclerView() {

        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        rcList.setHasFixedSize(true);
        rcList.setNestedScrollingEnabled(false);
        rcList.setItemAnimator(new DefaultItemAnimator());
        rcList.setLayoutManager(linearLayout);
        rcList.scrollToPosition(0);

        rcList.addOnScrollListener(new PaginationScroll(linearLayout) {
            @Override
            public void onLoadMore(int current_page) {
                pageNumber++;
                loadMoreData();
            }
        });

    }

    private void loadData() {

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            homeContentPresenter.loadRepositories(pageNumber);

        } else {

            dialogNoInternet();
            return;

        }
    }

    public void loadDataItems(final List<Items> items) {

        if (items != null) {

            adapter = new HomeAdapter(getContext(), items, HomeContentFragment.this);
            rcList.setAdapter(adapter);

            txtLoading.setVisibility(View.INVISIBLE);
            layAvi.setVisibility(View.INVISIBLE);
            avi.smoothToHide();

        } else {

            txtLoading.setText(R.string.nodata);
            avi.smoothToHide();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void loadPullRequest(String login, String name) {

        HomeActivity homeactivity = (HomeActivity) getActivity();
        PullRequestListFragment frag = new PullRequestListFragment();
        frag.setLogin(login);
        frag.setName(name);
        FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("pull_request_list");
        ft.setCustomAnimations(R.anim.left_to_right_home, R.anim.right_to_left_home);
        ft.replace(R.id.frContent, frag);
        ft.commit();

    }

    public void loadPullRequestError() {

        Toast.makeText(getContext(), "Nenhum Pull Request localizado!", Toast.LENGTH_SHORT).show();

    }

    public void noDataRepository() {

        Toast.makeText(getContext(), "Nenhum repositório localizado!", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.btnBack)
    public void onViewClicked() {
        dialogExit();
    }

    private void dialogExit() {

        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.dialog_exit);

        TextView txtExit = (TextView) dialog.findViewById(R.id.txtExit);
        TextView txtCancel = (TextView) dialog.findViewById(R.id.txtCancel);

        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        return;

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

    public void loadMoreData() {

        homeContentPresenter.updateRepositories(pageNumber);
        txtLoading.setVisibility(View.VISIBLE);
        layAvi.setVisibility(View.VISIBLE);
        avi.show();

    }

    public void updateDataItems(final List<Items> items) {

        adapter.swap(items);
        txtLoading.setVisibility(View.INVISIBLE);
        layAvi.setVisibility(View.INVISIBLE);
        avi.show();

    }

}
