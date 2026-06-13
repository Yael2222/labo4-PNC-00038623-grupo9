package com.server.app.serviceImpl;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.server.app.entities.Categoria;
import com.server.app.repositories.CategoriaRepository;
import com.server.app.services.CategoriaService;

@Service
@RequiredArgsConstructor

public class CategoriaServiceImpl
        implements CategoriaService {

    private final CategoriaRepository repository;

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

}
