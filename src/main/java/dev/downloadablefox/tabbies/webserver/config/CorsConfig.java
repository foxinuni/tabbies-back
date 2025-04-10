package dev.downloadablefox.tabbies.webserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("http://localhost:*") // Allow localhost on any port
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
            .allowedHeaders("*") // Allowed headers
            .allowCredentials(true) // Allow credentials
            .maxAge(3600); // Max age
    }
}
