package infnet.edu.br.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public class GitHubCatalog {
    @SerializedName("items")
    private List<Repository> repositories;

    // GETTER

    public List<Repository> getRepositories() {
        return repositories;
    }
}
