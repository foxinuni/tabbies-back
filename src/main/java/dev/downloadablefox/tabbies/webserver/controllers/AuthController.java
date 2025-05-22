package dev.downloadablefox.tabbies.webserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.downloadablefox.tabbies.webserver.dtos.Credentials;
import dev.downloadablefox.tabbies.webserver.dtos.UserInfo;
import dev.downloadablefox.tabbies.webserver.entities.UserEntity;
import dev.downloadablefox.tabbies.webserver.repositories.UserEntityRepository;
import dev.downloadablefox.tabbies.webserver.security.JwtGenerator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    UserEntityRepository userEntityRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse response, @RequestBody Credentials credentials) {
        // 1. Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                credentials.getEmail(),
                credentials.getPassword()
            )
        );

        // 2. Set the authentication object in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Generate JWT token
        String token = jwtGenerator.generateToken(authentication);

        // 4. Set the token in the response cookie
        response.addCookie(new Cookie("session", token) {{
            setPath("/");
            setMaxAge(60 * 60 * 24 * 30); 
            setHttpOnly(true);
            setSecure(true);
        }});

        return new ResponseEntity<String>("Login successful", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // 1. Invalidate the JWT token
        SecurityContextHolder.clearContext();

        // 2. Remove the token from the response cookie
        Cookie cookie = new Cookie("session", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new ResponseEntity<String>("Logout successful", HttpStatus.OK);
    }

    @GetMapping("/self")
    public ResponseEntity<UserInfo> getSelf() {
        // 1. Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("----------------------- " + email);

        // 2. Fetch user details from the database
        UserEntity userEntity = userEntityRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Map user entity to UserInfo DTO
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userEntity.getId());
        userInfo.setEmail(userEntity.getEmail());
        userInfo.setRole(userEntity.getRole().getName());

        return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
    }
}