package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.downloadablefox.tabbies.webserver.dtos.Credentials;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> auth(HttpServletResponse response, @RequestBody Credentials auth) {
        System.out.println("Login attempt: " + auth.toString());
        final Optional<User> user = authService.login(auth.getEmail(), auth.getPassword());

        if (user.isPresent()) {
            response.addCookie(new Cookie("session", auth.getEmail()) {{
                setPath("/");
                setMaxAge(60 * 60 * 24 * 30);
                setHttpOnly(true);
                setSecure(true);
            }});

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        response.addCookie(new Cookie("session", "") {{
            setPath("/");
            setMaxAge(0);
        }});

        return ResponseEntity.ok().build();
    }
}