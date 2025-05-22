package dev.downloadablefox.tabbies.webserver.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtGenerator jwtGenerator;

    @Autowired
    AuthService authService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final Optional<String> token = obtainToken(request);
        if (token.isEmpty() || !jwtGenerator.validateToken(token.get())) {
            // If the token is not present or invalid, continue the filter chain without authentication
            filterChain.doFilter(request, response);
            return;
        }
        
        // 1. Get user details from the token
        String username = jwtGenerator.getUsernameFromToken(token.get());
        UserDetails userDetails = authService.loadUserByUsername(username);

        // 2. Create an authentication object
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );

        // 3. Set the authentication object in the security context
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4. Continue the filter chain
        filterChain.doFilter(request, response);
    }


    private Optional<String> obtainToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("session".equals(cookie.getName())) {
                    return Optional.of(cookie.getValue());
                }
            }
        }

        return Optional.empty();
    }
}
