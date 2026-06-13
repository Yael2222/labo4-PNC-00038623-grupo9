package com.server.app.dto.cuenta;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CuentaResponseDto {

    private Integer id;

    private String alias;

    private String moneda;

    private Double saldoBase;

    private String tipo;

}
