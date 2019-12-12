package desafio.com.br.github.ui.pull_request;


import java.util.*;

import desafio.com.br.github.data.network.model.pull_request.*;
import desafio.com.br.github.interactor.pullrequest.*;

/**
 * Created by rafael on 24/01/18.
 */

public class PresenterPullRequest implements IpresenterPullRequest.PresenterView,IpresenterPullRequest.PresenterInteracor{

    IviewPullRequest view;
    IiteractorPullRequest iteractorPullRequest;

    public PresenterPullRequest(final IviewPullRequest view) {

        this.view = view;
    }

    @Override
    public void fetchData(String creator,String repository) {
        iteractorPullRequest = new IteractorPullRequest(this);
        iteractorPullRequest.fechtData(creator,repository);
    }

    @Override
    public void success(ArrayList<PullRequest> pullRequests) {
        view.Success(pullRequests);
    }

    @Override
    public void failure(String msg) {
        view.Failure(msg);
    }
}
