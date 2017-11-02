package testemongeral.com.br.testemongeral.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import testemongeral.com.br.testemongeral.model.Items;
import testemongeral.com.br.testemongeral.model.PullRequest;

public interface RepositoryService {

    @GET("search/repositories")
    public Call<Items> listRepositories(@Query("q") String language, @Query("sort") String sort, @Query("page") Integer page);

    @GET("repos/{owner}/{repo}/pulls")
    public Call<List<PullRequest>> listPullRequests(@Path("owner") String owner, @Path("repo") String repository);

}
