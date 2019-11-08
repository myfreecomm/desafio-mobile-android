package com.example.tsantana.desafiomobileandroid.tasks;

import android.os.AsyncTask;

import com.example.tsantana.desafiomobileandroid.data.dao.SearchDao;
import com.example.tsantana.desafiomobileandroid.data.model.Search;

/**
 * Created by tsantana on 08/12/2017.
 */

public class SearchTask extends AsyncTask<Integer,Void,Search>{

    private SearchDao searchDao;
    private Search mSearch;

    public SearchTask(){
        this.searchDao = new SearchDao();
    }
    @Override
    protected Search doInBackground(Integer... integers) {
        return searchDao.getSearchOfReposByPage(integers[0]);
    }


}
