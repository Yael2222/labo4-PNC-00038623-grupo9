package com.server.app.dto.movimiento;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MovimientoDto {

    private Double monto;

    private String monedaOriginal;

    private Double tasaCambio;

    private String descripcion;

    private Integer cuentaId;

    private Integer categoriaId;

}
