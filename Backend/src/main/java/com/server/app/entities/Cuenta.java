package com.server.app.entities;

import com.server.app.enums.Moneda;
import com.server.app.enums.TipoCuenta;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alias;

    @Enumerated(EnumType.STRING)
    private Moneda moneda;

    private Double saldoBase;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;

    @ManyToOne
    private User usuario;
}
