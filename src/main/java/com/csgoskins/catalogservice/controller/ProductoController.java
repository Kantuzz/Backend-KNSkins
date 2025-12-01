package com.csgoskins.catalogservice.controller;

import com.csgoskins.catalogservice.dto.ProductoCreateUpdateDto;
import com.csgoskins.catalogservice.dto.ProductoDto;
import com.csgoskins.catalogservice.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // luego se ajusta
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoDto> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long categoriaId
    ) {
        return productoService.list(q, categoriaId);
    }

    @GetMapping("/{id}")
    public ProductoDto get(@PathVariable Long id) {
        return productoService.get(id);
    }

    @PostMapping
    public ProductoDto create(@Valid @RequestBody ProductoCreateUpdateDto in) {
        return productoService.create(in);
    }

    @PutMapping("/{id}")
    public ProductoDto update(@PathVariable Long id,
                              @Valid @RequestBody ProductoCreateUpdateDto in) {
        return productoService.update(id, in);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoService.delete(id);
    }
}
