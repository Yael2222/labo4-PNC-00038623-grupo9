package com.server.app.serviceImpl;

import com.server.app.dto.cuenta.CuentaCreateDto;
import com.server.app.entities.Cuenta;
import com.server.app.entities.User;
import com.server.app.repositories.CuentaRepository;
import com.server.app.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CuentaServiceImpl
        implements CuentaService {

    private final CuentaRepository repository;

    @Override
    public Cuenta create(
            CuentaCreateDto dto,
            User user
    ){

        Cuenta c=new Cuenta();

        c.setAlias(dto.getAlias());

        c.setMoneda(dto.getMoneda());

        c.setTipo(dto.getTipo());

        c.setSaldoBase(0.0);

        c.setUsuario(user);

        return repository.save(c);

    }

    @Override
    public Page<Cuenta>
    findMine(
            User user,
            int page,
            int size
    ){

        return repository.findByUsuarioId(
                user.getId(),
                PageRequest.of(page,size)
        );

    }

}
