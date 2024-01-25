package com.MicroservicesProject.orderservice.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;

//import java.net.http.HttpClient;


import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Bean
//    @LoadBalanced
    public WebClient webClient() {

        return WebClient.builder().build();
    }
//    @Bean
//    @Primary
//    public WebClient webClient() {
//        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
//        return WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .build();
//    }
}
