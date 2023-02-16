package com.trianasalesianos.dam.Soccer.Football.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
<<<<<<< HEAD
=======

>>>>>>> 753df2818769881a112211a8e5fe1f35352d6202
@Configuration
public class WebConfig {

    @Bean
<<<<<<< HEAD
    public WebMvcConfigurer corsConfigurer()    {
=======
    public WebMvcConfigurer corsConfigurer(){
>>>>>>> 753df2818769881a112211a8e5fe1f35352d6202
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
