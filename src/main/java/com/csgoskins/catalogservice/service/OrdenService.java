package com.csgoskins.catalogservice.service;

import com.csgoskins.catalogservice.dto.OrdenCreateDto;
import com.csgoskins.catalogservice.model.*;
import com.csgoskins.catalogservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository ordenRepo;
    private final ProductoRepository productoRepo;

    public Orden crearOrden(OrdenCreateDto dto) {

        // VALIDAR STOCK
        Map<Long, Producto> productosMap = new HashMap<>();

        for (var item : dto.getItems()) {
            Producto p = productoRepo.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductId()));

            if (p.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + p.getNombre());
            }

            productosMap.put(item.getProductId(), p);
        }

        // DESCONTAR STOCK
        for (var item : dto.getItems()) {
            Producto p = productosMap.get(item.getProductId());
            p.setStock(p.getStock() - item.getCantidad());
            productoRepo.save(p);
        }

        // CREAR ORDEN
        Orden orden = Orden.builder()
                .clienteNombre(dto.getClienteNombre())
                .clienteEmail(dto.getClienteEmail())
                .direccionEnvio(dto.getDireccionEnvio())
                .total(dto.getTotal())
                .build();

        // Crear items
        List<OrdenItem> items = new ArrayList<>();

        for (var itemDto : dto.getItems()) {
            OrdenItem item = OrdenItem.builder()
                    .productId(itemDto.getProductId())
                    .cantidad(itemDto.getCantidad())
                    .precioUnitario(itemDto.getPrecioUnitario())
                    .orden(orden)
                    .build();
            items.add(item);
        }

        orden.setItems(items);

        return ordenRepo.save(orden);
    }

    // ADMIN
    public List<Orden> listarTodas() {
        return ordenRepo.findAll();
    }

    public Orden obtenerPorId(Long id) {
        return ordenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada: " + id));
    }
}
