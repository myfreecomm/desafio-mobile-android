package testemongeral.com.br.testemongeral.callback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testemongeral.com.br.testemongeral.delegate.Delegate;
import testemongeral.com.br.testemongeral.model.PullRequest;
import testemongeral.com.br.testemongeral.model.Repository;

public class PullRequestCallback implements Callback<List<PullRequest>> {

    private Delegate delegate;
    private List<PullRequest> pullRequests;
    private String error;

    public PullRequestCallback(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
        if(response.isSuccessful()) {
            this.pullRequests = response.body();
            this.delegate.success();
        } else {
            try {
                response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
                this.delegate.error();
            }
            this.delegate.error();
        }
    }

    @Override
    public void onFailure(Call<List<PullRequest>> call, Throwable t) {
        this.error = t.getMessage();
        this.delegate.error();
    }

    public List<PullRequest> getPullRequests() {
        return this.pullRequests;
    }

    public String getError() {
        return this.error;
    }

}
