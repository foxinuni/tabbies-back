package dev.downloadablefox.tabbies.webserver.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import dev.downloadablefox.tabbies.webserver.entities.RoleType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(options -> options.disable()))
            .authorizeHttpRequests(request -> request
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/contact/**").permitAll()

                // Auth endpoints
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/logout").permitAll()
                .requestMatchers("/auth/self").authenticated()
                
                // General endpoints
                .requestMatchers("/users/**").authenticated()
                .requestMatchers("/my-pets/**").authenticated()
                .requestMatchers("/pets/**").hasAnyAuthority(RoleType.VETERINARY.getRole().getName(), RoleType.ADMIN.getRole().getName())
                .requestMatchers("/procedures/**").hasAnyAuthority(RoleType.VETERINARY.getRole().getName(), RoleType.ADMIN.getRole().getName())
                .requestMatchers("/veterinarians/**").hasAnyAuthority(RoleType.VETERINARY.getRole().getName(), RoleType.ADMIN.getRole().getName())
                .requestMatchers("/medicines/**").hasAnyAuthority(RoleType.VETERINARY.getRole().getName(), RoleType.ADMIN.getRole().getName())

                .anyRequest().denyAll()
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthEntryPoint)
            );
        
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.cors(cors -> cors
            .configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.addAllowedOriginPattern("http://localhost:*");
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.addAllowedHeader("*");
                config.setAllowCredentials(true);
                config.setMaxAge(3600L);
                return config;
            })
        );
        
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
