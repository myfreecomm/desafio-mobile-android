package br.com.nexas.appgithubjava.retrofit;

import br.com.nexas.appgithubjava.service.RepositorioService;

/**
 * Created by user on 03/11/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.github.com";
    public static RepositorioService getGitHubService() {
        return RepositorioRetrofit.getClient(BASE_URL).create(RepositorioService.class);
    }
}
