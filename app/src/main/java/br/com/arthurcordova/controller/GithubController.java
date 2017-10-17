package br.com.arthurcordova.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import br.com.arthurcordova.R;
import br.com.arthurcordova.model.GithubRepositoryModel;
import br.com.arthurcordova.service.GithubService;
import br.com.arthurcordova.tools.ArthurCordovaDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 16/10/17.
 */

public class GithubController extends Controller implements Callback<GithubRepositoryModel> {

    private GithubService api;
    private Context ctx;
    private ArthurCordovaDialog dialog;
    private Activity activity;

    public void getGithubRepositories() {
        showDialog();
        Call<GithubRepositoryModel> call = api.getGithubRepositories();
        call.enqueue(this);
    }

    public GithubController(Context ctx, ArthurCordovaDialog dialog) {
        this.ctx = ctx;
        this.dialog = dialog;
        this.activity = ((Activity) ctx);
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
    public void onResponse(Call<GithubRepositoryModel> call, Response<GithubRepositoryModel> response) {
        dismissDialog();
        Log.e("SUCCESS", response.body().toString());
        if (response.isSuccessful()) {
            GithubRepositoryModel user = response.body();
//            new TaskUserDao(database, ctx, TaskUserDao.TASK_USER_SAVE).execute(user);

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
    public void onFailure(Call<GithubRepositoryModel> call, Throwable t) {
        dismissDialog();
        Log.e("ERROR", "");
    }
}
