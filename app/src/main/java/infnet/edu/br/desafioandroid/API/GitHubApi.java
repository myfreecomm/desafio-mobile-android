package infnet.edu.br.desafioandroid.API;

import infnet.edu.br.desafioandroid.model.GitHubCatalog;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public interface GitHubApi {
    @GET("search/repositories?q=language:Java")
    Call<GitHubCatalog> getCatalog();

    @GET("users/{username}/repos")
    Call<GitHubCatalog> getRepos(@Path("username") String username);
}

