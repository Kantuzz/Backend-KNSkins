package com.csgoskins.catalogservice.service;

import com.csgoskins.catalogservice.dto.ProductoCreateUpdateDto;
import com.csgoskins.catalogservice.dto.ProductoDto;
import com.csgoskins.catalogservice.model.Categoria;
import com.csgoskins.catalogservice.model.Producto;
import com.csgoskins.catalogservice.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;

    // LISTAR
    public List<ProductoDto> list(String nombre, Long categoriaId) {
        List<Producto> productos;

        if (nombre != null && !nombre.isBlank()) {
            productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (categoriaId != null) {
            Categoria cat = categoriaService.getById(categoriaId);
            productos = productoRepository.findByCategoria(cat);
        } else {
            productos = productoRepository.findAll();
        }

        return productos.stream().map(this::toDto).toList();
    }

    // OBTENER UNO
    public ProductoDto get(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDto(p);
    }

    // CREAR
    public ProductoDto create(ProductoCreateUpdateDto in) {
        Categoria cat = categoriaService.getById(in.getCategoriaId());

        Producto p = Producto.builder()
                .nombre(in.getNombre())
                .precio(in.getPrecio())
                .stock(in.getStock())
                .sku(in.getSku())
                .destacado(Boolean.TRUE.equals(in.getDestacado()))
                .imagenUrl(in.getImagenUrl())   // 👈 NUEVO
                .categoria(cat)
                .build();

        productoRepository.save(p);
        return toDto(p);
    }

    // ACTUALIZAR
    public ProductoDto update(Long id, ProductoCreateUpdateDto in) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Categoria cat = categoriaService.getById(in.getCategoriaId());

        p.setNombre(in.getNombre());
        p.setPrecio(in.getPrecio());
        p.setStock(in.getStock());
        p.setSku(in.getSku());
        p.setDestacado(Boolean.TRUE.equals(in.getDestacado()));
        p.setImagenUrl(in.getImagenUrl());  // 👈 NUEVO
        p.setCategoria(cat);

        productoRepository.save(p);
        return toDto(p);
    }

    // ELIMINAR
    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    // MAPEO ENTIDAD → DTO
    private ProductoDto toDto(Producto p) {
        return ProductoDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .sku(p.getSku())
                .destacado(Boolean.TRUE.equals(p.getDestacado()))
                .categoriaId(p.getCategoria().getId())
                .categoriaNombre(p.getCategoria().getNombre())
                .imagenUrl(p.getImagenUrl())  // 👈 NUEVO
                .build();
    }
}
