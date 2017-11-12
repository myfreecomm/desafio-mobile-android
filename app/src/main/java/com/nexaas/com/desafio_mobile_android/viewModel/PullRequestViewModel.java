package com.nexaas.com.desafio_mobile_android.viewModel;

import com.nexaas.com.desafio_mobile_android.client.PullRequentClient;
import com.nexaas.com.desafio_mobile_android.model.PullRequestEntity;

import java.util.List;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class PullRequestViewModel {

    private PullRequentClient pullRequentClient;

    public PullRequestViewModel(){
        this.pullRequentClient = new PullRequentClient();
    }

    public List<PullRequestEntity> getListRepository(String url){
        return  this.pullRequentClient.getListRepository(url);
    }
}
