package infnet.edu.br.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class PullRequest {

    @SerializedName("user")
    public User user;

    public User getUser() {
        return user;
    }

}
