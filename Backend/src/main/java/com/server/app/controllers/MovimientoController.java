package com.server.app.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.server.app.dto.movimiento.MovimientoDto;
import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
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

        Page<?> result = service.findAll(
                page,
                size
        );

        return ResponseEntity.ok(
                new Pagination<>(
                        result.getContent(),
                        new PaginationMeta(
                                result.getNumber(),
                                result.getSize(),
                                result.getTotalPages(),
                                result.getTotalElements()
                        )
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