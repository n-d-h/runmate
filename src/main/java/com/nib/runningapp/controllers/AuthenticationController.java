package com.nib.runningapp.controllers;

import com.nib.runningapp.dtos.GoogleUserDTO;
import com.nib.runningapp.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Authentication API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthService authService;

    @Operation(summary = "Authenticate user")
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authentication(@RequestBody GoogleUserDTO googleUserDTO) {
        Map<String, String> response = new HashMap<>();
        response.put("token", authService.authenticate(googleUserDTO));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Register user")
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(String username, String password) {
        Map<String, String> response = authService.register(username, password);
        if (response.get("status") != null) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
