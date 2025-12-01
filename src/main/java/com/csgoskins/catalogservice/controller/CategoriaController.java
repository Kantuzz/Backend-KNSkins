package com.csgoskins.catalogservice.controller;

import com.csgoskins.catalogservice.dto.CategoriaDto;
import com.csgoskins.catalogservice.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // luego se ajusta para tu frontend
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDto> list() {
        return categoriaService.findAll();
    }
}
