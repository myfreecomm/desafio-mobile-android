package com.nexaas.com.desafio_mobile_android.viewModel;

import com.nexaas.com.desafio_mobile_android.client.RepositoryClient;
import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;

import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class RepositoryVewModel {

    private RepositoryClient repositoryClient;

    public RepositoryVewModel(){
        this.repositoryClient = new RepositoryClient();
    }

    public List<RepositoryEntity> getListRepository(int page){
        return this.repositoryClient .getListRepository(page);
    }
}
