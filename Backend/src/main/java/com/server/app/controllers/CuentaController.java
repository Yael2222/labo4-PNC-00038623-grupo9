package com.server.app.controllers;

import com.server.app.dto.cuenta.CuentaCreateDto;
import com.server.app.entities.User;
import com.server.app.services.CuentaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finanzas/cuentas")
@RequiredArgsConstructor

public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<?> getMine(
            Authentication auth,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Object principal = auth.getPrincipal();

        if (!(principal instanceof User user)) {
            throw new RuntimeException("Usuario no autenticado");
        }

        return ResponseEntity.ok(
                cuentaService.findMine(
                        user,
                        page,
                        size
                )
        );
    }

    @PostMapping
    public ResponseEntity<?> create(
            Authentication auth,
            @RequestBody CuentaCreateDto dto
    ) {

        Object principal = auth.getPrincipal();

        if (!(principal instanceof User user)) {
            throw new RuntimeException("Usuario no autenticado");
        }

        return ResponseEntity.ok(
                cuentaService.create(
                        dto,
                        user
                )
        );
    }
}