package desafio.com.br.github.data.network;

import java.util.*;

import desafio.com.br.github.data.network.model.pull_request.*;
import desafio.com.br.github.data.network.model.repository.*;
import retrofit2.*;
import retrofit2.http.*;

/**
 * Created by rafael on 24/01/18.
 */

    public interface GithubService {

        @GET("/search/repositories?q=language:Java&sort=stars&page=1")
        Call<Repository> getRepository();

        @GET("/repos/{creator}/{repository}/pulls")
        Call<ArrayList<PullRequest>> getPullRequest(@Path("creator") String creator, @Path("repository") String repository);



    }

