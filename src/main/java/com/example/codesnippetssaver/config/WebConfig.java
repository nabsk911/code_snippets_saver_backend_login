package com.example.codesnippetssaver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // Adjust to match your API paths
                        .allowedOrigins("http://localhost:5173")  // Allow requests from your frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);  // Allow credentials (optional, remove if not needed)
            }
        };
    }
}
