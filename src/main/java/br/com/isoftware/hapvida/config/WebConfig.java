package br.com.isoftware.hapvida.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.isoftware.hapvida.util.CorsFiltro;

@Configuration
public class WebConfig {

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    CorsFiltro corsFilter() {
        return new CorsFiltro(this.corsConfigurationSource);
    }
}
