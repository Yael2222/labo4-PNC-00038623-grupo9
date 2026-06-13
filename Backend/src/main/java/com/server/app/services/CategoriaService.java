package com.server.app.services;


import java.util.List;

import com.server.app.entities.Categoria;

public interface CategoriaService {

    List<Categoria> findAll();

}
