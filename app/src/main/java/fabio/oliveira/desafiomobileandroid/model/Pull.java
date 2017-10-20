package fabio.oliveira.desafiomobileandroid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by fabio on 18/10/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pull extends RealmObject implements Serializable {

    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;
    @JsonProperty("created_at")
    private String data;
    @JsonProperty("user")
    private User user;
    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = oldFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.data = newFormat.format(date);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
