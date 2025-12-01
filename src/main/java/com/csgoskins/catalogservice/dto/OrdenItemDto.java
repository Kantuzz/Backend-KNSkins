package com.csgoskins.catalogservice.dto;

import lombok.Data;

@Data
public class OrdenItemDto {

    private Long productId;
    private Integer cantidad;
    private Integer precioUnitario;
}
