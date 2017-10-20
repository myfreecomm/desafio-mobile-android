package fabio.oliveira.desafiomobileandroid.network;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import fabio.oliveira.desafiomobileandroid.model.Pull;
import fabio.oliveira.desafiomobileandroid.model.Repository;
import fabio.oliveira.desafiomobileandroid.service.RepositoryService;
import fabio.oliveira.desafiomobileandroid.util.Contants;

/**
 * Created by fabio on 17/10/17.
 */

public class RepositoryRouter implements GitHubApiRouter<Repository> {

    @Override
    public Repository getResult(int page) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Repository repository = restTemplate.getForObject(Contants.URL_REPOSITORY_JAVA_STAR + String.valueOf(page), Repository.class);

        return repository;
    }

    @Override
    public Repository[] getResult(String url) {
        return null;
    }
}