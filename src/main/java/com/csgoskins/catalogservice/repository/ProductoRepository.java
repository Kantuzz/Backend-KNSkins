package com.csgoskins.catalogservice.repository;

import com.csgoskins.catalogservice.model.Producto;
import com.csgoskins.catalogservice.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
