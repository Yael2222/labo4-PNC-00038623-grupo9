package com.server.app.serviceImpl;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.app.dto.movimiento.MovimientoDto;
import com.server.app.dto.movimiento.TransferenciaDto;
import com.server.app.entities.*;
import com.server.app.repositories.*;
import com.server.app.services.MovimientoService;

@Service
@RequiredArgsConstructor

public class MovimientoServiceImpl
        implements MovimientoService {

    private final MovimientoRepository movimientoRepository;

    private final CuentaRepository cuentaRepository;

    private final CategoriaRepository categoriaRepository;

    @Override
    public Page<?> findAll(
            int page,
            int size
    ) {

        return movimientoRepository.findAll(
                PageRequest.of(page, size)
        );
    }

    @Override
    public void create(
            MovimientoDto dto
    ) {

        Movimiento m =
                new Movimiento();

        m.setMonto(dto.getMonto());

        m.setMonedaOriginal(
                dto.getMonedaOriginal()
        );

        m.setTasaCambio(
                dto.getTasaCambio()
        );

        m.setDescripcion(
                dto.getDescripcion()
        );

        m.setFecha(
                LocalDateTime.now()
        );

        m.setCuenta(
                cuentaRepository
                        .findById(
                                dto.getCuentaId()
                        )
                        .orElseThrow()
        );

        m.setCategoria(
                categoriaRepository
                        .findById(
                                dto.getCategoriaId()
                        )
                        .orElseThrow()
        );

        movimientoRepository.save(m);

    }

    @Override
    @Transactional
    public void transferir(
            TransferenciaDto dto,
            User user
    ) {

        Cuenta origen =
                cuentaRepository
                        .findById(
                                dto.getOrigen()
                        )
                        .orElseThrow();

        Cuenta destino =
                cuentaRepository
                        .findById(
                                dto.getDestino()
                        )
                        .orElseThrow();

        if (
                origen.getSaldoBase()
                        < dto.getMonto()
        ) {

            throw new RuntimeException(
                    "Fondos insuficientes"
            );

        }

        origen.setSaldoBase(
                origen.getSaldoBase()
                        - dto.getMonto()
        );

        destino.setSaldoBase(
                destino.getSaldoBase()
                        + dto.getMonto()
        );

        cuentaRepository.save(origen);

        cuentaRepository.save(destino);

    }

}
