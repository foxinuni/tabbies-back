package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.downloadablefox.tabbies.webserver.entities.Credentials;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(@RequestParam("invalid") Optional<Boolean> invalid, Model model) {
        model.addAttribute("auth", new Credentials());
        
        /* Set invalid message */
        if (invalid.isPresent() && invalid.get()) {
            model.addAttribute("invalid", true);
        }

        return "login";
    }
    
    @PostMapping("/login")
    public String auth(HttpServletResponse response, Credentials auth) {
        final Optional<User> user = authService.login(auth.getEmail(), auth.getPassword());

        if (user.isPresent()) {
            response.addCookie(new Cookie("session", auth.getEmail()) {{
                setPath("/");
                setMaxAge(60 * 60 * 24 * 30);
            }});

            return "redirect:/my-pets/";
        } else {
            return "redirect:/auth/login?invalid=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        response.addCookie(new Cookie("session", "") {{
            setPath("/");
            setMaxAge(0);
        }});

        return "redirect:/auth/login";
    }
}