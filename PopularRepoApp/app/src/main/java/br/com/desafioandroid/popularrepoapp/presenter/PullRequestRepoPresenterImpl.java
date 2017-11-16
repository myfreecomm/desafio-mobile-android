package br.com.desafioandroid.popularrepoapp.presenter;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.entity.PullRequestEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.mvp.MVP;
import br.com.desafioandroid.popularrepoapp.model.PullRequestRepoModelImpl;

/**
 * Created by Dennys on 15/11/2017.
 */

public class PullRequestRepoPresenterImpl implements MVP.PullRequestRepoPresenter {

    private MVP.PullRequestRepoView pullRequestRepoView;
    private MVP.PullRequestRepoModel pullRequestRepoModel;
    public List<PullRequestEntity> listPullRequestRepo;
    public int numOpen;
    public int numClose;

    public PullRequestRepoPresenterImpl(MVP.PullRequestRepoView pullRequestRepoView) {
        this.pullRequestRepoView = pullRequestRepoView;
        this.pullRequestRepoModel = new PullRequestRepoModelImpl(this);
    }

    @Override
    public void showProgressBar() {

        pullRequestRepoView.showProgressBar();
    }

    @Override
    public void hideProgressBar() {

        pullRequestRepoView.hideProgressBar();
    }

    @Override
    public void showMessage() {
        pullRequestRepoView.showMessage();
    }

    @Override
    public void getItensPullRequestRepo(List<PullRequestEntity> listPullRequestRepo) {

        this.listPullRequestRepo = listPullRequestRepo;
        countPullRequestOpenClose(listPullRequestRepo);
        pullRequestRepoView.loadViewsPullRequestRepo(listPullRequestRepo, numOpen, numClose);
    }

    @Override
    public void findPullResquestRepoByCreatorRepo(String creator, String repository) {

        pullRequestRepoModel.getPullRequestByCreatorRepo(creator, repository);
    }

    private void countPullRequestOpenClose(List<PullRequestEntity> listPullRequestRepo){

        for (PullRequestEntity pullRequestEntity: listPullRequestRepo){

            if(pullRequestEntity.state.equals("open")){
                numOpen ++;
            }else{
                numClose++;
            }
        }

    }
}
