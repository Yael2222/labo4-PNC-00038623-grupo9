package com.server.app.dto.categoria;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoriaResponseDto {

    private Integer id;

    private String nombre;

    private String tipo;

    private Integer categoriaPadreId;

}
