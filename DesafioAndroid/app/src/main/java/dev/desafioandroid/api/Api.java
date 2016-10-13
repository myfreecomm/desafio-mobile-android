package dev.desafioandroid.api;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.desafioandroid.BuildConfig;
import dev.desafioandroid.R;
import dev.desafioandroid.api.pojo.Error;
import dev.desafioandroid.api.pojo.GitHubInfo;
import dev.desafioandroid.api.pojo.PullRequest;
import dev.desafioandroid.api.service.PullRequestsService;
import dev.desafioandroid.api.service.RepositoriesService;
import dev.desafioandroid.util.ResponseListener;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Context mContext;
    private static Api instance;
    private Retrofit retrofit;
    private Call<?> currentCall;

    private Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static Api getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    public void getRepos(String lang, String sort, int page, final ResponseListener listener) {
        RepositoriesService service = retrofit.create(RepositoriesService.class);
        Call<GitHubInfo> call = service.getRepositories(lang, sort, page);
        setCurrentCall(call);
        call.enqueue(new Callback<GitHubInfo>() {
            @Override
            public void onResponse(Call<GitHubInfo> call, Response<GitHubInfo> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(getError(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<GitHubInfo> call, Throwable t) {
                listener.onFailure(treatFailureMessage(t));
            }
        });
    }

    public void getPulls(String owner, String repo, final ResponseListener listener) {
        PullRequestsService service = retrofit.create(PullRequestsService.class);
        Call<List<PullRequest>> call = service.getPullRequests(owner, repo);
        setCurrentCall(call);
        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(getError(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                listener.onFailure(treatFailureMessage(t));
            }
        });
    }

    public void cancelApiCall() {
        if (!currentCall.isExecuted() && !currentCall.isCanceled())
            currentCall.cancel();
    }

    private void setCurrentCall(Call<?> currentCall) {
        this.currentCall = currentCall;
    }

    private String getError(ResponseBody errorBody) {
        try {
            Converter<ResponseBody, Error> converter
                    = retrofit.responseBodyConverter(Error.class, new Annotation[0]);
            Error errorResponse = converter.convert(errorBody);

            if (null != errorResponse) {
                if (errorResponse.getMessage().contains("Use JsonReader.setLenient(true)"))
                    return mContext.getString(R.string.unknown_exception);

                return errorResponse.getMessage();
            }
            return mContext.getString(R.string.unknown_exception);
        } catch (IOException e) {
            Log.e("API", e.getMessage());
            return mContext.getString(R.string.unknown_exception);
        }
    }

    private String treatFailureMessage(Throwable t) {
        String message;
        if (t instanceof SocketTimeoutException) {
            message = mContext.getString(R.string.timeout_exception);
        } else if (t instanceof UnknownHostException) {
            message = mContext.getString(R.string.unknown_host_exception);
        } else {
            message = mContext.getString(R.string.unknown_exception);
            Log.e("API", t.getMessage());
        }
        return message;
    }
}