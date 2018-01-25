package desafio.com.br.github.interactor.pullrequest;


import java.util.*;

import desafio.com.br.github.data.network.*;
import desafio.com.br.github.data.network.model.pull_request.*;
import desafio.com.br.github.ui.pull_request.*;
import retrofit2.*;

/**
 * Created by rafael on 24/01/18.
 */

public class IteractorPullRequest implements IiteractorPullRequest {

    IpresenterPullRequest.PresenterInteracor presenterInteracor;

    public IteractorPullRequest(IpresenterPullRequest.PresenterInteracor presenterInteracor) {
        this.presenterInteracor = presenterInteracor;
    }

    @Override
    public void fechtData(String creator,String repository) {

        GithubApi.create().getPullRequest(creator,repository).enqueue(new Callback<ArrayList<PullRequest>>() {
            @Override
            public void onResponse(Call<ArrayList<PullRequest>> call, Response<ArrayList<PullRequest>> response) {
                presenterInteracor.success(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<PullRequest>> call, Throwable t) {
                presenterInteracor.failure(t.getLocalizedMessage());
            }
        });
    }
}
