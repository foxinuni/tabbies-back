package dev.downloadablefox.tabbies.webserver.services;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String generateToken(Long userId);
    Long getUserIdFromToken(String token);
    boolean isTokenValid(String token);
}