package com.csgoskins.catalogservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenItemDto {
    private Long productId;
    private Integer cantidad;
    private Integer precioUnitario;
}
