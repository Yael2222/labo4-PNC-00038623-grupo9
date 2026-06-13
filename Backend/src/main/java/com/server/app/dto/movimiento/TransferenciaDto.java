package com.server.app.dto.movimiento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaDto {

    private Integer origen;

    private Integer destino;

    private Double monto;

    private String descripcion;

}
