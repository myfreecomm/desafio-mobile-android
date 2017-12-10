package com.example.tsantana.desafiomobileandroid.data.dao;

import org.junit.Test;

import java.util.ArrayList;

import com.example.tsantana.desafiomobileandroid.data.dao.PullRequestDao;
import com.example.tsantana.desafiomobileandroid.data.model.PullRequest;
import static junit.framework.Assert.*;

/**
 * Created by tsantana on 08/12/2017.
 */

public class PullRequestDaoTest {
    private PullRequestDao pullRequestDao;
    private ArrayList<PullRequest> pullList;

    @Test
    public void testGetAllPullRequestsByRepositoryNameOwnerName() throws Exception {
        pullRequestDao = new PullRequestDao();
        pullList = pullRequestDao.getAllPullRequestsByRepositoryNameOwnerName(null,null);

        assertNotNull(pullList);

        PullRequest pr = pullList.get(0);
        assertNotNull(pr);
        assertNotNull(pr.getAutor());
    }
}
