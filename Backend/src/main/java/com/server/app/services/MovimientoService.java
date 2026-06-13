package com.server.app.services;


import com.server.app.dto.movimiento.MovimientoDto;
import com.server.app.dto.movimiento.TransferenciaDto;
import com.server.app.entities.User;

import org.springframework.data.domain.Page;

public interface MovimientoService {

    Page<?> findAll(
            int page,
            int size
    );

    void create(
            MovimientoDto dto
    );

    void transferir(
            TransferenciaDto dto,
            User user
    );

}
