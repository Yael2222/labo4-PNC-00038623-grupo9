package com.server.app.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.server.app.dto.movimiento.TransferenciaDto;
import com.server.app.entities.User;
import com.server.app.services.MovimientoService;

@RestController
@RequestMapping("/api/finanzas/transferencias")

@RequiredArgsConstructor

public class TransferenciaController {

    private final MovimientoService service;

    @PostMapping
    public ResponseEntity<?> transferir(

            Authentication auth,

            @RequestBody
            TransferenciaDto dto

    ) {

        User user =
                (User)
                        auth.getPrincipal();

        service.transferir(
                dto,
                user
        );

        return ResponseEntity.ok(
                "Transferencia realizada"
        );

    }

}
