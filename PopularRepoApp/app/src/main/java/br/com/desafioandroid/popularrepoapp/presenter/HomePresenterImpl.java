package br.com.desafioandroid.popularrepoapp.presenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.com.desafioandroid.popularrepoapp.entity.ItemRepositoryEntity;
import br.com.desafioandroid.popularrepoapp.entity.RepositoryEntity;
import br.com.desafioandroid.popularrepoapp.interfaces.mvp.MVP;
import br.com.desafioandroid.popularrepoapp.model.HomeModelImpl;
import br.com.desafioandroid.popularrepoapp.service.DataBaseServiceImpl;

/**
 * Created by Dennys on 15/11/2017.
 */

public class HomePresenterImpl implements MVP.HomePresenter {


    public static final int MAX_ITEMS_PER_REQUEST = 30;
    public int page = 1;
    private MVP.HomeView viewHome;
    private MVP.HomeModel modelHome;
    public List<ItemRepositoryEntity> listItenItemRepositoryEntities;

    public HomePresenterImpl() {

        initObjects();
    }

    public HomePresenterImpl(MVP.HomeView viewHome) {

        this.viewHome = viewHome;
        initObjects();
    }

    private void initObjects(){
        modelHome = new HomeModelImpl(this);
        listItenItemRepositoryEntities = new ArrayList<>();
    }

    @Override
    public void showProgressBar() {

        if(viewHome != null)
           viewHome.showProgressBar();
    }

    @Override
    public void hideProgressBar() {

        if(viewHome != null)
           viewHome.hideProgressBar();
    }

    @Override
    public void findRepositoryByPage(int page) {
        modelHome.getRepositoryByPage(page);
    }

    @Override
    public void showMessage() {
        viewHome.showMessage();
    }

    @Override
    public void getRepositoryByPage(RepositoryEntity repositoryEntity) {

        this.listItenItemRepositoryEntities.addAll(repositoryEntity.listRepositoryEntities);

        if (page == 2 && viewHome != null) {
            viewHome.loadRecycleViewRepository();
        }
    }

    @Override
    public List<ItemRepositoryEntity> getItemsToBeLoaded(int start, int end) {

        try {
            List<ItemRepositoryEntity> newItems = listItenItemRepositoryEntities.subList(start, end);
            final List<ItemRepositoryEntity> oldItems = viewHome.getItens();
            final List<ItemRepositoryEntity> itemsLocal = new LinkedList<>();
            itemsLocal.addAll(oldItems);
            itemsLocal.addAll(newItems);
            return itemsLocal;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean loadMoreItens() {

        findRepositoryByPage(page);
        final int start = ++page * MAX_ITEMS_PER_REQUEST;
        int end = start + MAX_ITEMS_PER_REQUEST;
        final List<ItemRepositoryEntity> itemsLocal = getItemsToBeLoaded(start, end);

        if (itemsLocal != null) {

            listItenItemRepositoryEntities.addAll(itemsLocal);
            return true;
        }
        return false;
    }

    @Override
    public void createOrUpdateDatabase() {

        modelHome.createOrUpdateDatabase();
    }
}
