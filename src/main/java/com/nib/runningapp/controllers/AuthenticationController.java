package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.GoogleUserDTO;
import com.nib.runningapp.security.jwt.JwtService;
import com.nib.runningapp.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(summary = "Authenticate user")
    @PostMapping("/authenticate")
    public ResponseEntity<?> authentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        GoogleUserDTO googleUserDTO = jwtService.parseJwtToken(token);
        return ResponseEntity.ok(authService.authenticate(googleUserDTO));
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("/test")
    public ResponseEntity<?> test(String token) {
        GoogleUserDTO googleUserDTO = jwtService.parseJwtToken(token);
        return ResponseEntity.ok(authService.authenticate(googleUserDTO));
    }

//    @Operation(summary = "Register user")
//    @PostMapping("/register")
//    public ResponseEntity<Map<String, String>> register(String username, String password) {
//        Map<String, String> response = authService.register(username, password);
//        if (response.get("status") != null) {
//            return ResponseEntity.badRequest().body(response);
//        }
//        return ResponseEntity.ok(response);
//    }
}
