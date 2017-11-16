package br.com.desafioandroid.popularrepoapp.model;

import br.com.desafioandroid.popularrepoapp.entity.RepositoryEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.delegator.AsyncTaskDelegate;
import br.com.desafioandroid.popularrepoapp.interfaces.mvp.MVP;
import br.com.desafioandroid.popularrepoapp.service.DataBaseServiceImpl;
import br.com.desafioandroid.popularrepoapp.service.RepositoryAsyncTask;

/**
 * Created by Dennys on 15/11/2017.
 */

public class HomeModelImpl implements MVP.HomeModel, AsyncTaskDelegate {

    public MVP.HomePresenter presenterHome;

    public HomeModelImpl(MVP.HomePresenter presenterHome) {
        this.presenterHome = presenterHome;
    }


    @Override
    public void getRepositoryByPage(int page) {

        new RepositoryAsyncTask(this).execute(String.valueOf(page));
    }

    @Override
    public void createOrUpdateDatabase() {

        try {
            new DataBaseServiceImpl().verificarVersaoBanco();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processStart() {
        presenterHome.showProgressBar();
    }

    @Override
    public void processFinish(Object object) {

        RepositoryEntity repositoryEntity = (RepositoryEntity) object;

        if(repositoryEntity != null){
            presenterHome.getRepositoryByPage(repositoryEntity);
        }else{
            presenterHome.showMessage();
        }
        presenterHome.hideProgressBar();
    }
}
