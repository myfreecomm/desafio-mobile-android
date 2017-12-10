package com.example.tsantana.desafiomobileandroid.data.dao;

import com.example.tsantana.desafiomobileandroid.data.dao.SearchDao;
import com.example.tsantana.desafiomobileandroid.data.model.Search;
import com.example.tsantana.desafiomobileandroid.data.model.User;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by tsantana on 10/12/2017.
 */

public class SearchDaoTest {
    private SearchDao searchDao;
    private Search search;

    private void assertOwnerItems(Search search){
        assertNotNull(search);
        assertNotNull(search.getRepositorios());
        assertNotNull(search.getRepositorios().get(0).getOwner());
        assertTrue(search.getRepositorios().get(0).getOwner() instanceof User);
    }

    @Test
    public void testGetSearchOfReposByPage(){
        searchDao = new SearchDao();
        search = searchDao.getSearchOfReposByPage(null);
        assertOwnerItems(search);

        search = searchDao.getSearchOfReposByPage(454547878);
        assertOwnerItems(search);

    }
}
