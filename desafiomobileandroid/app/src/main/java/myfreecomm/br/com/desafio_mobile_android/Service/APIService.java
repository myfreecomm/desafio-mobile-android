package myfreecomm.br.com.desafio_mobile_android.Service;

import java.util.List;

import myfreecomm.br.com.desafio_mobile_android.Model.Pull;
import myfreecomm.br.com.desafio_mobile_android.Model.Repositories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("/search/repositories")
    Call<Repositories> loadRepositoriesRetrofit(
            @Query("q") String language,
            @Query("sort") String sort,
            @Query("page") int pageNumber
    );

    @GET("/repos/{login}/{name}/pulls")
    Call<List<Pull>> loadPullRequestRetrofit(
            @Path("login") String login,
            @Path("name") String name
    );

}
