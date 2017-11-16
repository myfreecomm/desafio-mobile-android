package br.com.desafioandroid.popularrepoapp.interfaces.api;

import java.util.List;

import br.com.desafioandroid.popularrepoapp.entity.PullRequestEntity;
import br.com.desafioandroid.popularrepoapp.entity.RepositoryEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dennys on 15/11/2017.
 */

public interface ApiServices {

    @GET("search/repositories")
    Call<RepositoryEntity> repositories(@Query("q") String language, @Query("sort") String sort, @Query("page") int page);

    @GET("repos/{creator}/{repository}/pulls")
    Call<List<PullRequestEntity>> pullRequestRepos(@Path("creator") String creator, @Path("repository") String repository, @Query("state") String language);
}
