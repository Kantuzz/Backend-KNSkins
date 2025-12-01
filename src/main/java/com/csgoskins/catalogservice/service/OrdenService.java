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

        // VALIDAR Y DESCONTAR STOCK
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

        orden = ordenRepo.save(orden);

        // CREAR ITEMS DE LA ORDEN
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
        ordenRepo.save(orden);

        return orden;
    }
}
