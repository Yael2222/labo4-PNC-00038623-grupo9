package com.server.app.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.server.app.services.CategoriaService;

@RestController
@RequestMapping("/api/finanzas/categorias")

@RequiredArgsConstructor

public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(
                service.findAll()
        );

    }

}
