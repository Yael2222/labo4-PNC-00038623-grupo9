package com.server.app.controllers;

import com.server.app.dto.auth.LoginDto;
import com.server.app.dto.auth.LoginResponseDto;
import com.server.app.dto.auth.RegisterDto;
import com.server.app.entities.User;
import com.server.app.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody RegisterDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto dto){
        System.out.println("ENTRO AL LOGIN");
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(Map.of("error", "Usuario no autenticado"));
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof User)) {
            return ResponseEntity.status(401).body(Map.of("error", "Usuario no válido"));
        }

        User user = (User) principal;

        return ResponseEntity.ok(user);
    }
}