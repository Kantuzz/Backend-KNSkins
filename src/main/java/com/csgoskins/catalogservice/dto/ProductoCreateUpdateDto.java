package com.csgoskins.catalogservice.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCreateUpdateDto {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotNull
    @Positive
    private Integer precio;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @NotBlank
    @Size(min = 4, max = 8)
    private String sku;

    private Boolean destacado = false;

    @NotNull
    private Long categoriaId;

    @NotBlank
    private String imagenUrl;
}
