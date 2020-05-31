package infnet.edu.br.desafioandroid.API;

import java.util.List;

import infnet.edu.br.desafioandroid.model.GitHubCatalog;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by joaoluisdomingosxavier on 03/11/17.
 */

public interface GitHubApi {
    @GET("search/repositories?q=language:Java&sort=stars&page=1&per_page=20")
    Call<GitHubCatalog> getCatalog();


    @GET("repos/{owner}/{repo}/pulls")
    Call<List<GitHubCatalog>> getPullRequest(@Path("owner") String owner, @Path("repo") String repo);
}

