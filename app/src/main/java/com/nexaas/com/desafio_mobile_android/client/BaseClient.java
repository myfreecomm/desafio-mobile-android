package com.nexaas.com.desafio_mobile_android.client;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by marcos_viana on 11/11/17.
 */

public class BaseClient {

    protected RestTemplate restTemplate;
    protected static String url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&per_page=5&order=desc";

    public BaseClient(){
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }


}
