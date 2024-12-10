/**
 * @author Enkh-Amgalan G.
 *
 * @description This class configures global CORS (Cross-Origin Resource Sharing) settings
 * for the application, enabling requests from specific origins and controlling allowed methods, headers, and credentials.
 */

package com.ecommerce.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class for customizing CORS settings.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds CORS mappings to enable cross-origin requests from the specified origin
     * with specific HTTP methods and headers.
     *
     * @param registry CorsRegistry instance to configure CORS settings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}