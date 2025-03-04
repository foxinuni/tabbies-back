package dev.downloadablefox.tabbies.webserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    
}
