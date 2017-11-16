package br.com.desafioandroid.popularrepoapp.model;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.entity.PullRequestEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.delegator.AsyncTaskDelegate;
import br.com.desafioandroid.popularrepoapp.interfaces.mvp.MVP;
import br.com.desafioandroid.popularrepoapp.service.PullRequestRepoAsyncTask;

/**
 * Created by Dennys on 15/11/2017.
 */

public class PullRequestRepoModelImpl implements MVP.PullRequestRepoModel, AsyncTaskDelegate {

    private MVP.PullRequestRepoPresenter pullRequestRepoPresenter;

    public PullRequestRepoModelImpl(MVP.PullRequestRepoPresenter pullRequestRepoPresenter) {
        this.pullRequestRepoPresenter = pullRequestRepoPresenter;
    }

    @Override
    public void getPullRequestByCreatorRepo(String creator, String repository) {

        new PullRequestRepoAsyncTask(this).execute(creator, repository);
    }

    @Override
    public void processStart() {

        pullRequestRepoPresenter.showProgressBar();
    }

    @Override
    public void processFinish(Object object) {

        if(object != null) {
            List<PullRequestEntity> listPullRequestRepo = (List<PullRequestEntity>) object;
            pullRequestRepoPresenter.getItensPullRequestRepo(listPullRequestRepo);
        }else {
            pullRequestRepoPresenter.showMessage();
        }
        pullRequestRepoPresenter.hideProgressBar();
    }
}
