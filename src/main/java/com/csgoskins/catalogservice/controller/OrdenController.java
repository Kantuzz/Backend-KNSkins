package com.csgoskins.catalogservice.controller;

import com.csgoskins.catalogservice.dto.OrdenCreateDto;
import com.csgoskins.catalogservice.model.Orden;
import com.csgoskins.catalogservice.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public Orden crear(@RequestBody OrdenCreateDto dto) {
        return ordenService.crearOrden(dto);
    }

    // LISTAR TODAS LAS ÓRDENES (Panel admin)
    @GetMapping
    public List<Orden> listar() {
        return ordenService.listarTodas();
    }

    // OBTENER UNA ORDEN POR ID
    @GetMapping("/{id}")
    public Orden obtener(@PathVariable Long id) {
        return ordenService.obtenerPorId(id);
    }
}
