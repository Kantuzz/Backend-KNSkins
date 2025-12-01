package com.csgoskins.catalogservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {
    private Long id;
    private String nombre;
    private Integer precio;
    private Integer stock;
    private String sku;
    private Boolean destacado;
    private Long categoriaId;
    private String categoriaNombre;
    private String imagenUrl;
}
