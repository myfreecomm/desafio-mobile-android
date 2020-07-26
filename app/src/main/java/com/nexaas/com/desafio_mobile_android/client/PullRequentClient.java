package com.nexaas.com.desafio_mobile_android.client;

import com.nexaas.com.desafio_mobile_android.model.PullRequestEntity;
import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.nexaas.com.desafio_mobile_android.model.ResponseRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class PullRequentClient extends BaseClient {

    private String url = "https://api.github.com/repos/";

    public List<PullRequestEntity> getListRepository(String fullNameRepository){
        PullRequestEntity[] list = this.restTemplate.getForObject(url + fullNameRepository + "/pulls", PullRequestEntity[].class);
        return Arrays.asList(list);
    }
}
