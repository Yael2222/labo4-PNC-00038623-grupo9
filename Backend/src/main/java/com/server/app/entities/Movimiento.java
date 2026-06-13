package com.server.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double monto;

    private String monedaOriginal;

    private Double tasaCambio;

    private LocalDateTime fecha;

    private String descripcion;

    @ManyToOne
    private Cuenta cuenta;

    @ManyToOne
    private Categoria categoria;

}
