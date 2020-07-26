package com.nexaas.com.desafio_mobile_android;

import android.util.Log;

import com.nexaas.com.desafio_mobile_android.client.RepositoryClient;
import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.nexaas.com.desafio_mobile_android.viewModel.RepositoryVewModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import javax.net.ssl.SSLSocketFactory;

import static org.junit.Assert.assertEquals;
/**
 * Created by marcos_viana on 11/11/17.
 */




@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class,
        HttpComponentsClientHttpRequestFactory.class
        })
public class RepositoryViewModelTest {

    @InjectMocks
    RepositoryVewModel repositoryVewModel;
    @Mock
    RepositoryClient repositoryClient;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contensRepository() throws Exception {
        ArrayList<RepositoryEntity> list = new ArrayList<>();
        list.add(new RepositoryEntity());

        Mockito.when(repositoryClient.getListRepository(1)).thenReturn(list);
        assertEquals(true, repositoryVewModel.getListRepository(1).size() > 0);
    }

}