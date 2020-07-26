package com.nexaas.com.desafio_mobile_android.client;

import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.nexaas.com.desafio_mobile_android.model.ResponseRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class RepositoryClient extends BaseClient {

    public List<RepositoryEntity> getListRepository(int page){
        ResponseRepository responseRepository = this.restTemplate.getForObject(this.url + "&page=" + page, ResponseRepository.class);
        return Arrays.asList(responseRepository.getItems());
    }
}
