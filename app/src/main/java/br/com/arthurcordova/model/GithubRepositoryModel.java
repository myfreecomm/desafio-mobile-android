package br.com.arthurcordova.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by acstapassoli on 16/10/17.
 */

public class GithubRepositoryModel implements Serializable {

    private String total_count;
    private List<Items> items;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
