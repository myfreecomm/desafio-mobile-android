package teste.com.br.teste.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import teste.com.br.teste.model.Items;
import teste.com.br.teste.model.PullRequest;

public interface RepositoryService {

    @GET("search/repositories")
    public Call<Items> listRepositories(@Query("q") String language, @Query("sort") String sort, @Query("page") Integer page);

    @GET("repos/{owner}/{repo}/pulls")
    public Call<List<PullRequest>> listPullRequests(@Path("owner") String owner, @Path("repo") String repository);

}
