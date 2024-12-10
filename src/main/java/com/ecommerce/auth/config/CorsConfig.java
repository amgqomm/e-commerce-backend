/**
 * @author Enkh-Amgalan G.
 *
 * @description This class configures CORS (Cross-Origin Resource Sharing) for the application.
 * It defines the allowed origins, HTTP methods, and mappings to enable cross-origin requests.
 */

package com.ecommerce.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for handling CORS settings in the application.
 */
@Configuration
public class CorsConfig {

    /**
     * Creates a WebMvcConfigurer bean to define CORS mappings.
     *
     * @return WebMvcConfigurer instance with customized CORS settings.
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("*");
            }
        };
    }
}