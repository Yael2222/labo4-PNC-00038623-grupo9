package com.server.app.entities;

import com.server.app.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    @ManyToOne
    private Categoria categoriaPadre;

}
