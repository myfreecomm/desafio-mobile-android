package fabio.oliveira.desafiomobileandroid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by fabio on 16/10/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository extends RealmObject implements Serializable {

    @JsonProperty("items")
    private RealmList<Item> items;
    private int page;

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
