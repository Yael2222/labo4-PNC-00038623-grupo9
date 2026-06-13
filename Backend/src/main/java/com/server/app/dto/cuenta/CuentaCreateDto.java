package com.server.app.dto.cuenta;

import com.server.app.enums.Moneda;
import com.server.app.enums.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaCreateDto {

    private String alias;

    private Moneda moneda;

    private TipoCuenta tipo;

}