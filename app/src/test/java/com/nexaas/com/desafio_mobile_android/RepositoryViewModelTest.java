package com.nexaas.com.desafio_mobile_android;

import com.nexaas.com.desafio_mobile_android.model.RepositoryEntity;
import com.nexaas.com.desafio_mobile_android.viewModel.RepositoryVewModel;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RepositoryViewModelTest {

    private RepositoryVewModel repositoryVewModel = new RepositoryVewModel();

    @Test
    public void contensRepository() throws Exception {
        List<RepositoryEntity> list = repositoryVewModel.getListRepository(1);
        assertEquals(true, list.size() > 0);
    }
}