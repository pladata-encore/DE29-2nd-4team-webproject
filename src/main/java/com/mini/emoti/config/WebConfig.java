package com.mini.emoti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO Auto-generated method stub
        // WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
        .allowedMethods("*")
      .allowedOrigins("http://localhost:8880","http://localhost:3000", "http://localhost:3030", "http://localhost:5000", "http://host.docker.internal:9090");
      }

      
    
}
