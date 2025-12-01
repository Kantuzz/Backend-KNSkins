package com.csgoskins.catalogservice.controller;

import com.csgoskins.catalogservice.dto.OrdenCreateDto;
import com.csgoskins.catalogservice.model.Orden;
import com.csgoskins.catalogservice.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public Orden crearOrden(@RequestBody OrdenCreateDto dto) {
        return ordenService.crearOrden(dto);
    }
}
