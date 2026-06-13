package com.server.app.repositories;

import com.server.app.entities.Cuenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository
        extends JpaRepository<Cuenta,Integer> {

    Page<Cuenta> findByUsuarioId(
            Integer id,
            Pageable pageable
    );

}
