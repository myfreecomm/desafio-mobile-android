package fabio.oliveira.desafiomobileandroid.network;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import fabio.oliveira.desafiomobileandroid.model.Pull;

/**
 * Created by fabio on 17/10/17.
 */

public class PullRouter implements GitHubApiRouter<Pull> {

    @Override
    public Pull getResult(int page) {
        return null;
    }

    @Override
    public Pull[] getResult(String url) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Pull pull[] = restTemplate.getForObject(url, Pull[].class);

        return pull;
    }
}
