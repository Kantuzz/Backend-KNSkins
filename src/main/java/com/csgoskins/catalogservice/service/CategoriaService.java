package com.csgoskins.catalogservice.service;

import com.csgoskins.catalogservice.dto.CategoriaDto;
import com.csgoskins.catalogservice.model.Categoria;
import com.csgoskins.catalogservice.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll().stream()
                .map(c -> new CategoriaDto(c.getId(), c.getNombre()))
                .toList();
    }

    public Categoria getById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public Categoria getOrCreateByNombre(String nombre) {
        return categoriaRepository.findByNombreIgnoreCase(nombre)
                .orElseGet(() -> categoriaRepository.save(
                        Categoria.builder().nombre(nombre).build()
                ));
    }
}
