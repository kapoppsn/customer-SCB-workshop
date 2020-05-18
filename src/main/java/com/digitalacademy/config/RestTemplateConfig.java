package com.digitalacademy.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {


    @Value("${restTemplate.connectionTimeout}")
    private int connectionTimeout;

    @Value("${restTemplate.readTimeout}")
    private int readTimeOut;
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
