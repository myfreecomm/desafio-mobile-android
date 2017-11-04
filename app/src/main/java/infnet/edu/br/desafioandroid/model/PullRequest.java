package infnet.edu.br.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class PullRequest implements Serializable {

    @SerializedName("user")
    public User user;

    public User getUser() {
        return user;
    }

}
