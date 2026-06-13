package com.server.app.services;

import com.server.app.dto.cuenta.CuentaCreateDto;
import com.server.app.entities.Cuenta;
import com.server.app.entities.User;
import org.springframework.data.domain.Page;

public interface CuentaService {

    Cuenta create(
            CuentaCreateDto dto,
            User user
    );

    Page<Cuenta> findMine(
            User user,
            int page,
            int size
    );

}
