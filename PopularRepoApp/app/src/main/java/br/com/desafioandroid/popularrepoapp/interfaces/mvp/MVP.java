package br.com.desafioandroid.popularrepoapp.interfaces.mvp;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.entity.ItemRepositoryEntity;
import br.com.desafioandroid.popularrepoapp.entity.PullRequestEntity;
import br.com.desafioandroid.popularrepoapp.entity.RepositoryEntity;

/**
 * Created by Dennys on 15/11/2017.
 */

public interface MVP {

    interface HomeView {

        void showProgressBar();
        void hideProgressBar();
        List<ItemRepositoryEntity> getItens();
        void loadRecycleViewRepository();
        void showMessage();
    }

    interface HomePresenter{

        void showProgressBar();
        void hideProgressBar();
        void findRepositoryByPage(int page);
        void showMessage();
        void getRepositoryByPage(RepositoryEntity repositoryEntity);
        List<ItemRepositoryEntity> getItemsToBeLoaded(int start, int end);
        boolean loadMoreItens();
        void createOrUpdateDatabase();
    }

    interface HomeModel{

        void getRepositoryByPage(int page);
        void createOrUpdateDatabase();
    }


    interface PullRequestRepoView {

        void showProgressBar();
        void hideProgressBar();
        void showMessage();
        void loadViewsPullRequestRepo(List<PullRequestEntity> listPullRequestRepo, int opened, int closed);

    }

    interface PullRequestRepoPresenter {

        void showProgressBar();
        void hideProgressBar();
        void showMessage();
        void getItensPullRequestRepo(List<PullRequestEntity> listPullRequestRepo);
        void findPullResquestRepoByCreatorRepo(String creator, String repository);
    }

    interface PullRequestRepoModel {

        void getPullRequestByCreatorRepo(String creator, String repository);
    }
}
