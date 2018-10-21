package com.rad8329.microserviceusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUsersApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        final FilterRegistrationBean<CorsFilter> bean;        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();        
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        
        bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);

        return bean;
    }
}
