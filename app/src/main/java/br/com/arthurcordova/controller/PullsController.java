package br.com.arthurcordova.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import br.com.arthurcordova.R;
import br.com.arthurcordova.adapter.RVAPull;
import br.com.arthurcordova.adapter.RVARepository;
import br.com.arthurcordova.model.GithubRepositoryModel;
import br.com.arthurcordova.model.Items;
import br.com.arthurcordova.model.PullRequestModel;
import br.com.arthurcordova.service.GithubService;
import br.com.arthurcordova.tools.ArthurCordovaDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 16/10/17.
 */

public class PullsController extends Controller implements Callback<List<PullRequestModel>> {

    private GithubService api;
    private Context ctx;
    private ArthurCordovaDialog dialog;
    private Activity activity;
    private RVAPull adapter;
    private RecyclerView recyclerView;
    private Items items;

    public void getGithubPulls(Items item) {
        showDialog();
        Call<List<PullRequestModel>> call = api.getPulls(item.getOwner().getLogin(), item.getName());
        call.enqueue(this);
    }

    public PullsController(Context ctx, ArthurCordovaDialog dialog, RecyclerView recyclerView) {
        this.ctx = ctx;
        this.dialog = dialog;
        this.activity = ((Activity) ctx);
        this.recyclerView = recyclerView;
    }

    private void showDialog() {
        if (dialog != null) {
            dialog.show(activity.getFragmentManager(), null);
        }
    }

    private void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void start() {
        super.start();
        api = retrofit.create(GithubService.class);
    }

    @Override
    public void onResponse(Call<List<PullRequestModel>> call, Response<List<PullRequestModel>> response) {
        dismissDialog();
        if (response.isSuccessful()) {
            List<PullRequestModel> pullRequestModels = response.body();

            adapter  = new RVAPull(pullRequestModels);
            recyclerView.setAdapter(adapter);

        } else {
            new AlertDialog.Builder(ctx, R.style.ArthurCordovaDialog)
                    .setTitle(R.string.text_label_error)
                    .setMessage(R.string.text_error_msg)
                    .setPositiveButton(R.string.text_label_close, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        }
    }

    @Override
    public void onFailure(Call<List<PullRequestModel>> call, Throwable t) {
        dismissDialog();
        Log.e("ERROR", "");
    }
}
