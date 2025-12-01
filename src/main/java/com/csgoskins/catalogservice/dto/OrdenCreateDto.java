package com.csgoskins.catalogservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrdenCreateDto {

    private String clienteNombre;
    private String clienteEmail;
    private String direccionEnvio;
    private Integer total;

    private List<OrdenItemDto> items;
}
