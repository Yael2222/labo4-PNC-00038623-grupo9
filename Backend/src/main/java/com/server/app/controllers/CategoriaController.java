package com.server.app.controllers;


import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
import com.server.app.entities.Categoria;
import com.server.app.services.CategoriaService;

@RestController
@RequestMapping("/api/finanzas/categorias")

@RequiredArgsConstructor

public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<Categoria> result = service.findAll();

        return ResponseEntity.ok(
                new Pagination<>(
                        result,
                        new PaginationMeta(
                                0,
                                result.size(),
                                1,
                                result.size()
                        )
                )
        );

    }

}