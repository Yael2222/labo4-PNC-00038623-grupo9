package com.server.app.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.server.app.dto.movimiento.MovimientoDto;
import com.server.app.services.MovimientoService;

@RestController
@RequestMapping("/api/finanzas/movimientos")

@RequiredArgsConstructor

public class MovimientoController {

    private final MovimientoService service;

    @GetMapping
    public ResponseEntity<?> findAll(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size

    ) {

        return ResponseEntity.ok(
                service.findAll(
                        page,
                        size
                )
        );

    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody
            MovimientoDto dto
    ) {

        service.create(dto);

        return ResponseEntity.ok(
                "Movimiento creado"
        );

    }

}
